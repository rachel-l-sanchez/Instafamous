package com.socialmedia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmedia.models.Comment;
import com.socialmedia.models.Post;
import com.socialmedia.models.User;
import com.socialmedia.repositories.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository cRepo;
	
	public CommentService() {
		
	}
	
	
	
	public CommentService(CommentRepository cRepo) {
		super();
		this.cRepo = cRepo;
	}



	public Comment findById(Long id) {
		Optional<Comment> comment =cRepo.findById(id);
		if (comment.isPresent()) {
			return comment.get();
		}
		return null;
		
	}
	
	public Comment create(Comment c) {
		return cRepo.save(c);
	}
	
	public Comment addComment(Comment comment, Post post) {
	     post.getComments().add(comment);
         return cRepo.save(comment);

	}
	
	public List<Comment> findAllByPost(Post post) {
		return cRepo.findAllByPost(post);
	}
	
	
}
