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
	

	public Follower findById(Long id) {
		Optional<Follower> follower =followRepo.findById(id);
		if (follower.isPresent()) {
				return follower.get();
		}
		return null;
		
	}

	
	public Follower createFollowing(Follower follower, User user) {

		follower.getFollowing().add(user);
		return followRepo.save(follower);
	}
	
	public void removeFollower(User user, Follower follower) {
		// TODO Auto-generated method stub
		user.getFollowers().remove(follower);

	}
	
	public Follower addFollower(User user, Follower follower) {
		// TODO Auto-generated method stub
		follower.addFollower(user);
		follower.getFollowing().add(user);
		return followRepo.save(follower);
	}

	
	public Follower create(Follower follower) {
		return followRepo.save(follower);
	}
	
	public List<Follower> findAllByFollowing(User user) {
		return followRepo.findAllByFollowing(user);
	}
	
}
