package com.socialmedia.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "posts")
public class Post {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id", unique = true, nullable = false)
		private Long id;
				

		@Size(min = 3, message = "Length must be at least 3 characters")
		private String caption;
	
		@Size(min=4, message = "File name is required")
		private String imageURL;
	  
		@OneToMany(mappedBy="post", fetch = FetchType.LAZY)
	    @JsonIgnore
	    private List<Comment> comments = new java.util.ArrayList<Comment>();

	    
		@Column(updatable = false)
		@DateTimeFormat(pattern="yyyy-MM-dd")
		private Date createdAt;
		@DateTimeFormat(pattern="yyyy-MM-dd")
		private Date updatedAt;
		
		@ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(
	       name = "userLikesPost", 
	       joinColumns = @JoinColumn(name = "post_id"), 
	       inverseJoinColumns = @JoinColumn(name = "user_id")
		)
		private List<User> likes;


		@ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name="user_id")
	    private User creator;
		
		
		@ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(
	       name = "usersposts", 
	       joinColumns = @JoinColumn(name = "post_id"), 
	       inverseJoinColumns = @JoinColumn(name = "user_id")
		)
		private List<User> usersPosting;

		
		public Post(Long id,
				@NotEmpty(message = "Caption must not be blank") @NotBlank @Size(min = 3, message = "Length must be at least 3 characters") String caption,
				@NotBlank(message = "File name must not be blank") @NotEmpty(message = "File name must not be empty") @Size(min = 4, message = "File name is required") String imageURL,
				List<Comment> comments, Date createdAt, Date updatedAt, List<User> users, User creator,
				List<User> usersPosting) {
			super();
			this.id = id;
			this.caption = caption;
			this.imageURL = imageURL;
			this.comments = comments;
			this.createdAt = createdAt;
			this.updatedAt = updatedAt;
			this.likes = users;
			this.creator = creator;
			this.usersPosting = usersPosting;
		}

		

		public User getCreator() {
			return creator;
		}



		public List<User> getUsersPosting() {
			return usersPosting;
		}



		public void setUsersPosting(List<User> usersPosting) {
			this.usersPosting = usersPosting;
		}



		public void setCreator(User creator) {
			this.creator = creator;
		}



		public Post () {
			
		}



		public List<Comment> getComments() {
			return comments;
		}


		public void setComments(List<Comment> comments) {
			this.comments = comments;
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getCaption() {
			return caption;
		}


		public void setCaption(String caption) {
			this.caption = caption;
		}


		public Date getCreatedAt() {
			return createdAt;
		}


		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}


		public Date getUpdatedAt() {
			return updatedAt;
		}


		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}


		@PrePersist
		protected void onCreate() {
			this.createdAt = new Date();
		}
		@PreUpdate
		protected void onUpdate() {
			this.createdAt = new Date();
		}


		public String getImageURL() {
			return imageURL;
		}


		public void setImageURL(String imageURL) {
			this.imageURL = imageURL;
		}


		public void setComments(Post addComment) {
			// TODO Auto-generated method stub
			
		}



		public void setComments(User u) {
			// TODO Auto-generated method stub
			
		}


		public void add(Post post) {
			// TODO Auto-generated method stub
			
		}



		public List<User> getLikes() {
			return likes;
		}



		public void setLikes(List<User> likes) {
			this.likes = likes;
		}



		public void setUsers(List<User> likes) {
			this.likes = likes;
		}



		public Post orElse(Object object) {
			// TODO Auto-generated method stub
			return null;
		}




}

