package com.socialmedia.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
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
import javax.persistence.Transient;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	private String name;
    private String username;
    private String password;
    @Email
    private String email;
    @Transient
    private String confirm;
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
	
    private String filePath = "";
    

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "followers_users", 
            joinColumns = @JoinColumn(name = "user_id"), 
            inverseJoinColumns = @JoinColumn(name = "follower_id"))
	private List<Follower> followers = new ArrayList<>(); 
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
       name = "userLikesPost", 
       joinColumns = @JoinColumn(name = "user_id"), 
       inverseJoinColumns = @JoinColumn(name = "post_id")
	)
	private List<Post> likes;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comment> comments;
	
	
	@OneToMany(mappedBy="creator", fetch = FetchType.LAZY)
    private List<Post> createdPosts;

	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
       name = "usersposts", 
       joinColumns = @JoinColumn(name = "user_id"), 
       inverseJoinColumns = @JoinColumn(name = "post_id")
	)
	private List<Post> postedPosts;


	public User(Long id, String name, String username, String password, @Email String email, String confirm,
			Date createdAt, Date updatedAt, List<Role> roles, String filePath, List<Follower> followers,
			List<Post> likes, List<Comment> comments, List<Post> createdPosts, List<Post> postedPosts) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.confirm = confirm;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.roles = roles;
		this.filePath = filePath;
		this.followers = followers;
		this.likes = likes;
		this.comments = comments;
		this.createdPosts = createdPosts;
		this.postedPosts = postedPosts;
	}


	public List<Post> getPostedPosts() {
		return postedPosts;
	}


	public void setPostedPosts(List<Post> postedPosts) {
		this.postedPosts = postedPosts;
	}


	public List<Post> getCreatedPosts() {
		return createdPosts;
	}

	public void setCreatedPosts(List<Post> createdPosts) {
		this.createdPosts = createdPosts;
	}


	public User() {
		// TODO Auto-generated constructor stub
	}



	public List<Comment> getComments() {
		return comments;
	}



	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}



	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public User(Long id) {
		super();
		this.id = id;
	}
	
	

	public List<Post> getLikes() {
		return likes;
	}


	public void setLikes(List<Post> likes) {
		this.likes = likes;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUsername() {
		return username;
	}
	
	


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getConfirm() {
		return confirm;
	}


	public void setConfirm(String confirm) {
		this.confirm = confirm;
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


	public List<Role> getRoles() {
		return roles;
	}


	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.createdAt = new Date();
	}


	public List<Follower> getFollowers() {
		// TODO Auto-generated method stub
		return followers;
	}

	public void setFollowers(List<Follower> followers) {
		this.followers = followers;
	}

	

	
}
