package com.socialmedia.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.socialmedia.models.Comment;
import com.socialmedia.models.Post;
import com.socialmedia.models.User;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

	Comment save(Comment c);    
	List<Comment> findAll();
	Optional<Comment> findById(Long id);
	List<Post> findAllByUser(User user);
	List<Comment> findAllByPost(Post post);
}
