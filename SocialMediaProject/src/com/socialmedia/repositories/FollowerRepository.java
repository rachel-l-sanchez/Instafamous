package com.socialmedia.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.socialmedia.models.Follower;
import com.socialmedia.models.User;

@Repository
public interface FollowerRepository extends CrudRepository<Follower, Long> {
	Follower save(User user);
	List<Follower> findAll();
	Optional<Follower> findById(Long id);
	String findByUsername(String username);
}
