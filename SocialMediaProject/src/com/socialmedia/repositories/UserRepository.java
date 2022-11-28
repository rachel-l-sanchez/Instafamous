package com.socialmedia.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Repository;

import com.socialmedia.models.Comment;
import com.socialmedia.models.Follower;
import com.socialmedia.models.Post;
import com.socialmedia.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	User save(User user);
	User findByEmail(String email);
	Optional<User> findById(Long id);
	String findByName(String name);
	List<User> findAllByFollowers(User follower);
	User findByUsername(String username);
	List<User> findAllByLikes(Post post);
	List<User> findAllByFollowers(Follower follower);
	User findByToken(String token);


}