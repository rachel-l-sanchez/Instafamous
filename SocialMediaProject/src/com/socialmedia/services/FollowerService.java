package com.socialmedia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmedia.models.Follower;
import com.socialmedia.models.User;
import com.socialmedia.repositories.FollowerRepository;
import com.socialmedia.repositories.UserRepository;

@Service
public class FollowerService {
	
	@Autowired
	private FollowerRepository followRepo;
	
	@Autowired
	private UserRepository uRepo;
	
	public List<Follower> find() {
		return followRepo.findAll();
		
	}

	
	public Follower findById(Long id) {
		Optional<Follower> follower =followRepo.findById(id);
		if (follower.isPresent()) {
				return follower.get();
		}
		return null;
		
	}
	
	public Follower createFollower(Follower follower, User user) {
	    user.getFollowers().add(follower);
	    follower.getFollowing().add(user);
		return followRepo.save(follower);
	}
	
	public void removeFollower(User user, Follower follower) {
		// TODO Auto-generated method stub
		user.getFollowers().remove(follower);
		followRepo.deleteById(follower.getId());

	}
//	find all the followers of one particular user
//	using the many to many relationship
	public List<Follower> findAllByFollowing(User user) {
		return followRepo.findAllByFollowing(user);
		
	}
}
