package com.socialmedia.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.socialmedia.models.Comment;
import com.socialmedia.models.Post;
import com.socialmedia.models.User;
import com.socialmedia.repositories.CommentRepository;
import com.socialmedia.repositories.PostRepository;

@Service
@Transactional
public class PostService {
	@Autowired
	private PostRepository pRepo;
	
	@Autowired 
	private CommentRepository cRepo;
	
	private static final int numPages = 5;
	
	public PostService() {
		
	}
	
	
// create a post with the many to many relationship of users to posts
	public Post create(Post post) {
		return pRepo.save(post);
	}

	public List<Post> find() {
		return pRepo.findAll();
	}

//	grab one post object
	public Post findById(Long id) {
		Optional<Post> post =pRepo.findById(id);
		if (post.isPresent()) {
				return post.get();
		}
		return null;
		
	}
	
//	have a user like another's post
	public Post addLike(Post post, User user) {
		user.getLikes().add(post);
		return pRepo.save(post);
	}
	
// find all comments for a post
	public List<Comment> findByComments(Post post) {
		if (post != null) {
			return  pRepo.findByCommentsNotContains(post);
		}
		else {
			return null;
		}
	

	}
	
//	create the comments
	
	public Comment addComment(Post post, Comment comment) {
		Comment newComment = new Comment(comment.getPost());
        newComment.setPost(pRepo.findById(post.getId()));
        newComment.setPost(post);
        post.getComments().add(newComment);
        return cRepo.save(newComment);
	}
	
	public List<Post> getAll(User user) {
		return pRepo.findAllByUsers(user);
	}
	
	public Page<Post> postsPerPage(int pageNumber) {
		PageRequest pageRequest = PageRequest.of(pageNumber ,numPages, Sort.Direction.ASC, "createdAt");
		Page<Post> posts = pRepo.findAll(pageRequest);
		return pRepo.findAll(pageRequest);
	}
	

	
	
}