package com.cyanApp1.postManagement.service;


import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.cyanApp1.postManagement.model.Post;

@Service
public class PostManageService {
	private static List<Post> posts = new ArrayList<>();
	
	private static int postCount=0;

//	static {
//		posts.add(new Post("the_social_toad", ++postCount , "Kandy","NULL", "Never Changing",
//						LocalDate.now()));
//		posts.add(new Post("the_social_toad", ++postCount , "Colombo", "NULL","Always high tempreture",
//				LocalDate.now()));
//		posts.add(new Post("the_social_toad", ++postCount , "Malabe", "NULL", "SLIIT",
//				LocalDate.now()));
//	}
	
	public List<Post> findByUsername(String username){
		Predicate<? super Post> predicate = 
				post -> post.getUserName().equalsIgnoreCase(username);
				System.out.println("Inside Post Manage Service "+ posts.stream().filter(predicate).toList());
		return posts.stream().filter(predicate).toList();
	}
	
	
	public Post addPosts(String userName, int postNumber, String location, File imageFile, String description,
			LocalDate targetDate) {
		Post post = new Post(userName, ++postCount,location,imageFile,description,targetDate);
		posts.add(post);
		return post;
	}
	
	public void deleteById(int id) {
		Predicate<? super Post> predicate = post -> post.getPostNumber() == id;
		posts.removeIf(predicate);
	}

	public Post findById(int id) {
		Predicate<? super Post> predicate = post -> post.getPostNumber() == id;
		Post post = posts.stream().filter(predicate).findFirst().get();
		return post;
	}
	
	public void updatePost(Post post) {
		System.out.println("POST NUMBER " + post.getPostNumber());
		deleteById(post.getPostNumber());
		posts.add(post);
	}
}
