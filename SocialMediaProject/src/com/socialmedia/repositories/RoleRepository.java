package com.socialmedia.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.socialmedia.models.Role;

//imports removed for brevity
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	    List<Role> findAll();
	    
	    List<Role> findByName(String name);



}

