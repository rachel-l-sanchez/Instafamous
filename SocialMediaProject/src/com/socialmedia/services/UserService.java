package com.socialmedia.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
	private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;


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
	
	
	public User createFollower(Follower follower, User user) {
	    user.getFollowers().add(follower);
		return uRepo.save(user);
	}
	
	
	public String forgotPassword(String email) {
		Optional<User> userOptional =Optional
				.ofNullable(uRepo.findByEmail(email));

		if (!userOptional.isPresent()) {
			return "Invalid email id.";
		}

		User user = userOptional.get();
		user.setToken(generateToken());
		user.setTokenCreationDate(LocalDateTime.now());

		user = uRepo.save(user);

		return user.getToken();
	}

	public String resetPassword(String token, String password) {

		Optional<User> userOptional;
		userOptional = Optional
				.ofNullable(uRepo.findByToken(token));

		if (!userOptional.isPresent()) {
			return "Invalid token.";
		}

		LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();

		if (isTokenExpired(tokenCreationDate)) {
			return "Token expired.";

		}

		User user = userOptional.get();

		user.setPassword(password);
		user.setToken(null);
		user.setTokenCreationDate(null);

		uRepo.save(user);

		return "Your password successfully updated.";
	}

	private String generateToken() {
		StringBuilder token = new StringBuilder();

		return token.append(UUID.randomUUID().toString())
				.append(UUID.randomUUID().toString()).toString();
	}

	private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

		LocalDateTime now = LocalDateTime.now();
		Duration diff = Duration.between(tokenCreationDate, now);

		return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
	}
}
	

	
