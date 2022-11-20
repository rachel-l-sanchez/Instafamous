package com.socialmedia.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.socialmedia.models.Follower;
import com.socialmedia.models.Post;
import com.socialmedia.models.User;
import com.socialmedia.services.FollowerService;
import com.socialmedia.services.PostService;
import com.socialmedia.services.UserService;

@Controller
public class UserController {
	
	private UserService uService;

	@Autowired
	private PostService pService;

	@Autowired
	private FollowerService followService;

	public UserController(UserService uService, PostService pService) {
		super();
		this.uService = uService;
		this.pService = pService;
	}
	
	 @GetMapping("/login")
	 public String login(@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model, @ModelAttribute("user") User user,BindingResult result) {
	        if(error != null) {
	            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
	        }
	        if(logout != null) {
	            model.addAttribute("logoutMessage", "Logout Successful");
	        }
	        return "login.jsp";
	    }
	 
	 @GetMapping("/error")
	 public String error(@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model, @ModelAttribute("user") User user,BindingResult result) {
	        if(error != null) {
	            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
	        }
	        return "login.jsp";
	    }
	
		
	 @PostMapping("/login")
	 public String loginUser(@Valid @ModelAttribute("user") User user, @RequestParam(defaultValue = "1") int pageNumber,@RequestParam("username") String username, Model model,BindingResult result,  HttpSession session) {
	        if (result.hasErrors()) {
	            return "login.jsp";
	        }
	        session.setAttribute("username", username);
	        uService.findByUsername(username);
	        System.out.println("username" + username);
     		return "redirect:/dashboard/" + pageNumber;

	 }
	 
	 @RequestMapping("/register")
	    public String registerForm(@ModelAttribute("user") User user) {
	        return "index.jsp";
	 }
	    
	 @PostMapping("/register")
	 public String registration(@Valid @ModelAttribute("user") User user, @RequestParam("username") String username, @RequestParam(defaultValue = "1") int pageNumber,BindingResult result, Model model, HttpSession session) {
	     if (result.hasErrors()) {
	        return "index.jsp";
	     }
	     uService.saveWithUserRole(user);
	     session.setAttribute("username", username);
	     return "redirect:/dashboard/"+ pageNumber ;
	 }
    

    
	@RequestMapping("/")
    public String home(Principal principal, Model model, @Param("username") String username, 
    		@ModelAttribute("user") User user,
    		@ModelAttribute("post") Post post, 
    		BindingResult result, HttpSession session, @Param("id") Long post_id, @RequestParam(defaultValue = "1") int pageNumber) {
		String currentUsername = principal.getName(); 
        model.addAttribute("currentUser", uService.findByUsername(currentUsername));
        User otherUser = uService.findByUsername(username);
        session.setAttribute("user_id", otherUser.getId());
        return "redirect:/dashboard/" + pageNumber;
    }
	
	@PostMapping("/add/follower/{id}")
	public String createFollower(@ModelAttribute("newFollower") Follower newFollower,
			@Param("followUsername") String followUsername, 
			@PathVariable("id") Long user_id, @ModelAttribute("user") User user,
			Principal principal, Model model,
			@RequestParam(defaultValue = "1") int pageNumber) {
		
		String username = principal.getName();
		User currentUser = uService.findByUsername(username);
		User oneUser = uService.findByUsername(followUsername);
		if (followService.findById(currentUser.getId()) != null) {
			Follower follower = followService.findById(currentUser.getId());
			follower.setUsername(currentUser.getUsername());
			follower.setName(currentUser.getName());
			followService.createFollower(followService.findById(currentUser.getId()), oneUser);
		}
		return "redirect:/dashboard/" + pageNumber;
	}

	@DeleteMapping("/unfollow")
	public String unfollowUser(@ModelAttribute("user") User user, @ModelAttribute("followerInDB") Follower followerInDB,
			Principal principal, HttpSession session, @RequestParam(defaultValue = "1") int pageNumber) {
		String username = principal.getName();
		User currentUser = uService.findByUsername(username);
		Long user_id = (Long) session.getAttribute("user_id");
		User oneUser = uService.findById(user_id);
		if (followService.findById(currentUser.getId()) != null) {
			Follower follower = followService.findById(currentUser.getId());
			followService.removeFollower(oneUser, follower);
		}
		return "redirect:/dashboard/" + pageNumber;
	}
	
	@GetMapping("/settings/{id}")
	public String updateSettings(@PathVariable("id")Long id, Model model, Principal principal, @ModelAttribute("user") User user, BindingResult result) {		
		String username = principal.getName();	
		model.addAttribute("currentUser", uService.findByUsername(username));
		return "settings.jsp";
	}
	
	@PutMapping("update/settings/{id}")
	public String changeSettings(@Valid @ModelAttribute("user") User user, BindingResult result, 
			@RequestParam(defaultValue = "1") int pageNumber, 
			@PathVariable("id") Long id) {
		
		uService.validateUpdate(user, result);
		
		if (result.hasErrors()) {
			return "settings.jsp";
		}
		user.setName(user.getName());
		user.setEmail(user.getEmail());
		user.setPassword(user.getPassword());
		user.setUsername(user.getUsername());
		uService.updateOne(user);
		return "redirect:/dashboard/" + pageNumber;
		
	}
	
	@GetMapping("my/followers")
	public String seeFollowers(Principal principal, @ModelAttribute("user") User user, 
			@ModelAttribute("follower") Follower follower, Model model) {
		String username = principal.getName();
		User currentUser = uService.findByUsername(username);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("followers", followService.findAllByFollowing(currentUser));
		return "followers.jsp"; 
	}
	
	@GetMapping("/following")
	public String followingMe(Principal principal, @ModelAttribute("user") User user,
			@ModelAttribute("follower") Follower follower, Model model) {
		String username = principal.getName();
		User currentUser = uService.findByUsername(username);
        model.addAttribute("currentUser", currentUser);
	    model.addAttribute("following", uService.findAllByFollower(followService.findById(currentUser.getId())));
		return "following.jsp"; 
	}
	

}
