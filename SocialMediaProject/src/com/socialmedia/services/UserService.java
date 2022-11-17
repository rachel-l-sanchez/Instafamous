package com.socialmedia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.socialmedia.models.Comment;
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
	      
	public void saveUserWithAdminRole(User user) {
	    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	    user.setRoles(rRepo.findByName("ROLE_ADMIN"));
	    uRepo.save(user);
	}    
	    
	public User findByUsername(String username) {
	    return uRepo.findByUsername(username);
	}


			
	public User create(User u) {
		return uRepo.save(u);
	}

	public List<User> find() {
		return uRepo.findAll();
	}
	
	
	public List<User> findAllBy(User follower) {
		return uRepo.findAllByFollowers(follower);
	}
	
	public User findById(Long id) {
		Optional<User> user =uRepo.findById(id);
		if (user.isPresent()) {
				return user.get();
		}
		return null;
		
	}
	

	public User addFollower(User user, Follower follower) {
		// TODO Auto-generated method stub
		user.getFollowers().add(follower);
		return uRepo.save(user);
	}
	
	public User removeFollower(User user, Follower follower) {
		// TODO Auto-generated method stub
		user.getFollowers().remove(follower);
		return uRepo.save(user);
	}
	
	public User updateOne(User user) {
		return uRepo.save(user);
	}
	

}
