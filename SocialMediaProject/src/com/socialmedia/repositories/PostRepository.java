package com.socialmedia.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.socialmedia.models.Comment;
import com.socialmedia.models.Post;
import com.socialmedia.models.User;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Long>, CrudRepository<Post, Long> {
	Post save(Post c);    
	List<Post> findAll();
	Optional<Post> findById(Long id);
	List<Comment> findAllByComments(Post post);
	void deleteById(Long id);
	Post save(Long id);
	List<Comment> findByCommentsNotContains(Post post);
	List<Post> findAllByLikes(User user);
	List<Post> findByUsersPostingNotContains(User user);
	Post findByImageURLAndCaption(String imageURL, String caption);
}
