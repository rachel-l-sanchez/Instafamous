package com.socialmedia.services;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Slice;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.socialmedia.models.Comment;
import com.socialmedia.models.Post;
import com.socialmedia.models.User;
import com.socialmedia.repositories.CommentRepository;
import com.socialmedia.repositories.PostRepository;
import com.socialmedia.repositories.UserRepository;

@Service
@Transactional
public class PostService {
	@Autowired
	private PostRepository pRepo;
	
	@Autowired 
	private CommentRepository cRepo;

	
	private static final int PAGE_SIZE = 6;
	
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
		return pRepo.findAllByLikes(user);
	}
	
	public int getLikes(Post post) {
		return post.getLikes().size();
	}
	

	
	public List<Post> findByUsersPosting(User user) {
		if (user != null) {
			return  pRepo.findByUsersPostingNotContains(user);
		}
		else {
			return null;
		}
	

	}
	

	public Object validatePost(Post post, BindingResult result) {

		if (post.getCaption().equals("")) {
			result.rejectValue("caption","completed", "Caption is blank");
		} if (post.getImageURL().equals("")) {
			result.rejectValue("imageURL", "completed", "Image not uploaded");
		} if (result.hasErrors()) {
			return null;
		} 
	
		return result;
	
	}
	
	public Page<Post> postsPerPage(int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber, PAGE_SIZE, Sort.Direction.DESC, "createdAt");
        Page<Post> posts = pRepo.findAll(pageRequest);
        return pRepo.findAll(pageRequest);
    }
	
	public Post updateOne(Post post) {
		return pRepo.save(post);
	}
	
	public void destroy(Long id) {
		System.out.println(id);
		pRepo.deleteById(id);
	}
	
}
	