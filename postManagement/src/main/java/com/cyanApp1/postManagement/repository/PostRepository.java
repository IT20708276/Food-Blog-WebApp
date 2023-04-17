package com.cyanApp1.postManagement.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cyanApp1.postManagement.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

		List<Post> findByUserName(String userName);
		
}
