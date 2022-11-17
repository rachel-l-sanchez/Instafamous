package com.socialmedia.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.socialmedia.models.Comment;
import com.socialmedia.models.Follower;
import com.socialmedia.models.Post;
import com.socialmedia.models.User;
import com.socialmedia.services.CommentService;
import com.socialmedia.services.FileService;
import com.socialmedia.services.FollowerService;
import com.socialmedia.services.PostService;
import com.socialmedia.services.UserService;

@Controller
public class PostController {

		@Autowired
		private PostService pService;
		
		@Autowired
		private UserService uService;
		
		@Autowired
		private FileService fService;
		
		@Autowired
		private FollowerService followService;
		
		@Autowired
		private CommentService cService;
		
		@GetMapping("/create")
		public String createPost(@ModelAttribute("newPost") Post newPost, Model model, Principal principal) {
	        String username = principal.getName();
	        model.addAttribute("currentUser", uService.findByUsername(username));
			return "create.jsp";
		}
		
		@PostMapping("/create/post")
		public String newPost(@Valid @ModelAttribute("newPost") Post newPost,
				@ModelAttribute("user") User user, Principal principal,
				@RequestParam("imageURL") MultipartFile imageURL,
				BindingResult result,
				RedirectAttributes redirectAttributes, 
				HttpSession session) {
			
			if (result.hasErrors()) {
				return "create.jsp";
			}
			newPost.setImageURL(this.fService.save(imageURL));
			String username = principal.getName();
            newPost.setCreator(uService.findByUsername(username));
            newPost.setCaption(newPost.getCaption());
			this.pService.create(newPost);
			redirectAttributes.addFlashAttribute("message", "Post has been added");

			return "redirect:/detail/" + newPost.getId();
		}
		
		
		
		@GetMapping("/detail/{id}")
		public String showPost(Principal principal,@PathVariable("id") Long post_id, 
				@ModelAttribute("user") User user, @ModelAttribute("comment")Comment comment, @ModelAttribute("post") Post post,
				@ModelAttribute("newFollower") Follower newFollower, Model model, HttpSession session) {
			Post onePost = pService.findById(post_id);
			model.addAttribute("currentPost", onePost);
	        String username = principal.getName();
	        model.addAttribute("followingUser",	uService.findById(user.getId()));
	        model.addAttribute("currentUser", uService.findByUsername(username));
			model.addAttribute("allComments", cService.findAllByPost(onePost));
			model.addAttribute("follower", followService.findById(newFollower.getId()));
			
			return "show.jsp";
		}
		
		@PostMapping("/add/comment/{id}")
		public String addToPosts(Principal principal, @ModelAttribute("post") Post post, @PathVariable("id") Long postId,
				@Valid @ModelAttribute("user") User user, @ModelAttribute("comment") Comment comment, BindingResult result) {
			if (result.hasErrors()) {
				return "show.jsp";
			}
			Comment newComment = new Comment();
            newComment.setUser(uService.findByUsername(principal.getName()));
            newComment.setContent(comment.getContent());
            newComment.setPost(pService.findById(postId));
            post.getComments().add(newComment);
            cService.create(newComment);
			return "redirect:/detail/" + postId;
		}
		
		@PostMapping("/add/follower")
		public String createFollower(@Valid @ModelAttribute("newFollower") Follower newFollower,
				@Param("followUsername") String followUsername,@ModelAttribute("user") User user,
				Principal principal, Model model, BindingResult result) {
			if (result.hasErrors()) {
				return "show.jsp";
			}
			String username = principal.getName();
			User currentUser = uService.findByUsername(username);
			User followUser = uService.findByUsername(followUsername);
			Follower follower = followService.createFollower(newFollower);
			uService.addFollower(currentUser, follower);
			return "redirect:/profile/" + currentUser.getId();
		}
		
		@GetMapping("/add/follower/{id}")
		public String addToFollowers(Principal principal, @PathVariable("id") Long id, Model model, BindingResult result) {
			String username = principal.getName();
			User oneUser = uService.findByUsername(username);
			model.addAttribute("user", oneUser);
			Follower follower = followService.findById(id);
			uService.addFollower(oneUser, follower);
			return "redirect:/profile/" + oneUser.getId();
		}
		
		@GetMapping("/profile/{id}/{pageNumber}")
		public String showPostsforUser(Principal principal, @ModelAttribute("post") Post post, @PathVariable("pageNumber") int pageNumber, @PathVariable("id") Long user_id, @ModelAttribute("follower") Follower follower, Model model) {
			User oneUser = uService.findById(user_id);
			model.addAttribute("oneUser", oneUser);
		 	Page<Post> posts = pService.postsPerPage(pageNumber-1);
	    	int totalPages = posts.getTotalPages();
	    	model.addAttribute("totalPages", totalPages);
	    	model.addAttribute("pages", posts);
			model.addAttribute("onePost", pService.findById(post.getId()));
			model.addAttribute("allPosts", pService.getAll(oneUser));
			String username = principal.getName();
			model.addAttribute("follower", followService.findById(follower.getId()));
			model.addAttribute("currentUser",uService.findByUsername(username));
			return "profile.jsp";
		}
		
		@GetMapping("add/like/{id}/{postId}")
		public String addLike(Principal principal, @ModelAttribute("post") Post post, @PathVariable("id") Long user_id,
				@PathVariable("postId") Long postId, Model model) {
			Post onePost = pService.findById(postId);
			String username = principal.getName();
			User currentUser = uService.findByUsername(username);
	        model.addAttribute("currentUser", currentUser);
			pService.addLike(onePost, currentUser);
			return "redirect:/detail/" + post.getId(); 
		}
		
		

		

}

