package com.socialmedia.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
		public String createPost(Principal principal, @ModelAttribute("newPost") Post newPost,
				Model model) {
	        String username = principal.getName();
	        model.addAttribute("currentUser", uService.findByUsername(username));
			return "create.jsp";
		}
		
		
		@PostMapping("/create/post")
		public String newPost(@Valid 
				@ModelAttribute("user") User user, Principal principal,
				@RequestParam("imageURL") MultipartFile imageURL,
				@ModelAttribute("newPost") Post newPost, BindingResult result,
				RedirectAttributes redirectAttributes, 
				HttpSession session, @RequestParam(defaultValue = "1") int pageNumber)  {
			
			pService.validatePost(newPost, result);
			if (result.hasErrors()) {
				return "create.jsp";
			}
			String username = principal.getName();
			newPost.setCreator(uService.findByUsername(username));
	        newPost.setImageURL(this.fService.save(imageURL));
	        newPost.setCaption(newPost.getCaption());
	        pService.create(newPost);
			redirectAttributes.addFlashAttribute("message", "Post has been added");
			return("redirect:/dashboard/" + pageNumber);
		}
			

		@GetMapping("/detail/{id}/{userId}")
		public String showPost(Principal principal,@PathVariable("id") Long post_id, 
				@Param("id") String username,
				@ModelAttribute("user") User user, @ModelAttribute("comment")Comment comment,
				@ModelAttribute("newFollower") Follower newFollower, 
				Model model, HttpSession session) {
			
			Post onePost = pService.findById(post_id);
//			show and be able to like a post using the many to many relationship in the model working 
//			through the service to the controller
			model.addAttribute("likes",uService.findAllLikes(onePost));
			model.addAttribute("currentPost", onePost);
	        String currentUsername = principal.getName();
	        model.addAttribute("currentUser", uService.findByUsername(currentUsername));
//	        get the user being followed
	        if ((Long) session.getAttribute("user_id") != null) {
	        	Long user_id = (Long) session.getAttribute("user_id");
	        	User otherUser = uService.findById(user_id);
	        	model.addAttribute("followingUser", otherUser);
	        }
//	        show all comments for one individual post
			model.addAttribute("allComments", cService.findAllByPost(onePost));
			
			return "show.jsp";
		}
		
//		add comments to one post by post id
		
		@PostMapping("/add/comment/{id}")
		public String addToPosts(Principal principal, @ModelAttribute("post") Post post,
				@PathVariable("id") Long postId,@ModelAttribute("user") User user,
				@Valid @ModelAttribute("comment") Comment comment, BindingResult result) {
			if (result.hasErrors()) {
				return "show.jsp";
			}
			Comment newComment = new Comment();
            newComment.setUser(uService.findByUsername(principal.getName()));
            newComment.setContent(comment.getContent());
            newComment.setPost(pService.findById(postId));
            post.getComments().add(newComment);
            cService.create(newComment);
			return "redirect:/detail/" + postId+ "/"  + user.getId();
		}

//	show all posts created by one user
// one to many relationship -- each post only has one creator
		@GetMapping("/profile/{id}")
		public String showPostsforUser(Principal principal, @ModelAttribute("post") Post post,
				@PathVariable("id") Long user_id, Model model) {
			
			model.addAttribute("onePost", pService.findById(post.getId()));
			String username = principal.getName();
			model.addAttribute("currentUser",uService.findByUsername(username));
			model.addAttribute("userPosting", uService.findById(user_id));
			return "profile.jsp";
			
		}
		
		@GetMapping("add/like/{id}/{postId}")
		public String addLike(Principal principal, 
				@ModelAttribute("post") Post post, @PathVariable("id") Long user_id,
				@PathVariable("postId") Long postId, Model model, 
				@RequestParam(defaultValue = "1") int pageNumber) {
			Post onePost = pService.findById(postId);
			String username = principal.getName();
			User currentUser = uService.findByUsername(username);
	        model.addAttribute("currentUser", currentUser);
			pService.addLike(onePost, currentUser);
			return "redirect:/dashboard/" + pageNumber; 
		}
		

		
		@GetMapping("/dashboard/{pageNumber}")
		public String dashboard(Principal principal, Model model, 
				@Param("username") String username, 
		@ModelAttribute("user") User user,
		@ModelAttribute("post") Post post, 
		@PathVariable("pageNumber") int pageNumber) {
			
//			pulling all the associated users
			String currentUsername = principal.getName(); 
			model.addAttribute("likes",post.getLikes());
			model.addAttribute("currentUser",uService.findByUsername(currentUsername));
			model.addAttribute("oneUser",uService.findByUsername(username));

//			adding pagination to app of user posts
			Page<Post> posts = pService.postsPerPage(pageNumber - 1);
		    model.addAttribute("totalPages", posts.getTotalPages());
		    model.addAttribute("allPosts", posts.getContent());
			return "dashboard.jsp";
			}
		
		
		
		

		

}

