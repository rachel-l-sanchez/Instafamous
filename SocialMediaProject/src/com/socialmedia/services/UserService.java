package com.socialmedia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.socialmedia.models.Follower;
import com.socialmedia.models.Post;
import com.socialmedia.models.User;
import com.socialmedia.repositories.RoleRepository;
import com.socialmedia.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository uRepo;

	
	private RoleRepository rRepo;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	    
 
	private static final int numPages = 5;
	


	public UserService(UserRepository uRepo, RoleRepository rRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.uRepo = uRepo;
		this.rRepo = rRepo;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	public void saveWithUserRole(User user) {
	    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	    user.setRoles(rRepo.findByName("ROLE_USER"));
	    uRepo.save(user);
	}
//	use of spring security  
	public void saveUserWithAdminRole(User user) {
	    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	    user.setRoles(rRepo.findByName("ROLE_ADMIN"));
	    uRepo.save(user);
	}    
	    
//	find one user by a username string
	public User findByUsername(String username) {
	    return uRepo.findByUsername(username);
	}

			
	public User create(User u) {
		return uRepo.save(u);
	}

	public List<User> find() {
		return uRepo.findAll();
	}
	
//	find all the users that another user follows
	public List<User> findAllBy(User followingUser) {
		return uRepo.findAllByFollowers(followingUser);
	}
	
	public User findById(Long id) {
		Optional<User> user =uRepo.findById(id);
		if (user.isPresent()) {
				return user.get();
		}
		return null;
		
	}

//	update user account
	public User updateOne(User user) {
		return uRepo.save(user);
	}
//	find the likes of a current post
	public List<User> findAllLikes(Post post) {
		return uRepo.findAllByLikes(post);
	}
	
//	find all the users that one user is following
	public List<User> findAllByFollower(Follower followerUser) {
		return uRepo.findAllByFollowers(followerUser);
	}
	
	

	public Object validateUpdate(User user, BindingResult result) {

		if (user.getName().equals("")) {
			result.rejectValue("name","completed", "Name is required");
		} if (user.getPassword().equals("")) {
			result.rejectValue("password", "completed", "Password is required");
		} if (user.getUsername().equals("")) {
			result.rejectValue("username", "Completed", "Username is required");
		} if (user.getEmail().equals("")) {
			result.rejectValue("email", "Completed", "Email is required");
		} if (result.hasErrors()) {
			return null;
		}
	
		return result;
	
	}

}
	

	
