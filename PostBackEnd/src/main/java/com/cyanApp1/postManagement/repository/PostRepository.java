package com.cyanApp1.postManagement.repository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cyanApp1.postManagement.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

		 List<Post> findByUserName(String userName);
		 List<Post> findByLocation(String location);
		  List<Post> findByLocationContainingIgnoreCase(String partialLocation);
		
	
}
