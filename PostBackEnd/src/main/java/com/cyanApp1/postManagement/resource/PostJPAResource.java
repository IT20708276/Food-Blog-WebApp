package com.cyanApp1.postManagement.resource;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cyanApp1.postManagement.model.Post;
import com.cyanApp1.postManagement.model.PostImages;
import com.cyanApp1.postManagement.repository.PostImageRepository;
import com.cyanApp1.postManagement.repository.PostRepository;
import com.cyanApp1.postManagement.service.PostManageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@RestController
public class PostJPAResource {

	private PostManageService postService;

	private PostRepository postrepository;
	@Autowired
	private PostImageRepository postImagerepository;
	public PostJPAResource(PostManageService postService,PostRepository postrepository) {
		this.postService = postService;
		this.postrepository = postrepository;
	}
	
	@GetMapping("/users/{username}/posts")
	public ResponseEntity<List<Post>> retrievePosts(@PathVariable String username) throws IOException {
		 List<Post> post = postrepository.findByUserName(username);
		 if (!post.isEmpty()) {
	           
	            return ResponseEntity.ok().body(post);
	            
	        } else {
	            return ResponseEntity.notFound().build();
	        }

		
	}
	
	@GetMapping("/users/{username}/posts/{number}")
	public Post retrievePost(@PathVariable String username
			,@PathVariable int number) {
		//return postService.findById(number);
		return postrepository.findById(number).get();
	}
	
	@GetMapping("/users/{username}/other-posts")
	public ResponseEntity<List<Post>> retrieveOtherUsersPosts(@PathVariable String username) {
		List<Post> posts = postrepository.findAll();
		posts.removeIf(post -> post.getUserName().equals(username));
		if (!posts.isEmpty()) {
	        return ResponseEntity.ok().body(posts);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	

//	@GetMapping("/users/posts")
//	public List<Post> retrieveAllPosts(){
//	    ObjectMapper mapper = new ObjectMapper();
//	    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//	    return postrepository.findAll();
//	
//	}
	
//	@GetMapping("/users/{username}/posts/location/{location}")
//	public ResponseEntity<List<Post>> retrievePostsByLocation(@PathVariable String username,
//	                                                           @PathVariable String location) throws IOException {
//	    List<Post> posts = postrepository.findByLocation(location);
//	    if (!posts.isEmpty()) {
//	        return ResponseEntity.ok().body(posts);
//	    } else {
//	        return ResponseEntity.notFound().build();
//	    }
//	}
//	
	@GetMapping("/users/{username}/posts/location/{location}")
	public ResponseEntity<List<Post>> retrievePostsByLocationPartial(@PathVariable String username,
	                                                           @PathVariable String location) throws IOException {
	    List<Post> posts = postrepository.findByLocationContainingIgnoreCase(location);
	    if (!posts.isEmpty()) {
	        return ResponseEntity.ok().body(posts);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}


	@DeleteMapping("/users/{username}/posts/{number}")
	public ResponseEntity<Void> deletePost(@PathVariable String username
			,@PathVariable int number){
		//postService.deleteById(number);
		postrepository.deleteById(number);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/users/{username}/posts/{number}")
	public Post updatePost(@PathVariable String username
							,@PathVariable int number, @RequestBody Post post) {
		System.out.println("POST NUMBER RESOURCE " + number);
		post.setPostNumber(number);
		System.out.println("POST NUMBER RESOURCE 2 "+ post.getPostNumber());
		//postService.updatePost(post);
		postrepository.save(post);
		return post;
	}
	
	
 
	@PostMapping("/users/{username}/posts")
	public Post createPost(@PathVariable String username
			,@RequestBody Post post) throws IOException {
		post.setUserName(username);
		List<PostImages> pimg = post.getPostImages();
		System.out.println(Arrays.toString(pimg.toArray()));
		
		return postrepository.save(post);
		
	
}

}
