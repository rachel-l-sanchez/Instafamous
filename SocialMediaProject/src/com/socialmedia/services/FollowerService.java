package com.socialmedia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmedia.models.Follower;
import com.socialmedia.models.User;
import com.socialmedia.repositories.FollowerRepository;

@Service
public class FollowerService {
	
	@Autowired
	private FollowerRepository followRepo;
	
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
	
	public Follower createFollower(Follower follower) {
		return followRepo.save(follower);
	}
}
