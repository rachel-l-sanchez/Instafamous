package com.socialmedia.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	 public String loginUser(@Valid @ModelAttribute("user") User user, @RequestParam("username") String username, Model model,BindingResult result,  HttpSession session) {
	        if (result.hasErrors()) {
	            return "login.jsp";
	        }
	        session.setAttribute("username", username);
	        uService.findByUsername(username);
	        System.out.println("username" + username);
     		return "redirect:/dashboard";

	 }
	 
	 @RequestMapping("/register")
	    public String registerForm(@ModelAttribute("user") User user) {
	        return "index.jsp";
	 }
	    
	 @PostMapping("/register")
	 public String registration(@Valid @ModelAttribute("user") User user, @RequestParam("username") String username, BindingResult result, Model model, HttpSession session) {
	     if (result.hasErrors()) {
	        return "index.jsp";
	     }
	     uService.saveWithUserRole(user);
	     session.setAttribute("username", username);
	     return "redirect:/dashboard";
	 }
    

    
	@RequestMapping(value = {"/", "/dashboard"})
    public String home(Principal principal, Model model, @ModelAttribute("user") User user, @ModelAttribute("post") Post post, BindingResult result, HttpSession session) {
    	model.addAttribute("posts",this.pService.find());
		model.addAttribute("users",pService.find());
		String username = principal.getName(); 
		model.addAttribute("followers", user.getFollowers());
		User oneUser = uService.findByUsername(username);
        model.addAttribute("currentUser", uService.findByUsername(username));
        return "dashboard.jsp";
    }
	
	
	@DeleteMapping("/unfollow/{id}")
	public String destroy(@PathVariable("postId") Long post_id, @PathVariable("id") Long userId, @RequestParam("followId") Long followId) {
		User currentUser = uService.findById(userId);
		Follower currentFollower = followService.findById(followId);
		uService.removeFollower(currentUser, currentFollower);
		return "redirect:/detail/" + post_id;
	}
	
	
	@GetMapping("/settings/{id}")
	public String updateSettings(@PathVariable("id")Long id, @ModelAttribute("user") User user, Model model) {
		model.addAttribute("currentUser", uService.findById(id));
		return "settings.jsp";
	}
	
	@PutMapping("update/settings/{id}")
	public String changeSettings(@Valid @ModelAttribute("user") User user, BindingResult result,HttpSession session, @PathVariable("id") Long id, Principal principal) {
		
		if (result.hasErrors()) {
			return "edit.jsp";
		} else {
			uService.updateOne(user);
			uService.saveWithUserRole(user);
			String username = principal.getName();
		    session.setAttribute("username", username);
			return "redirect:/dashboard";
		}
	}

}
