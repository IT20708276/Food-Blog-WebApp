package com.cyanApp1.postManagement.resource;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
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
import com.cyanApp1.postManagement.repository.PostRepository;
import com.cyanApp1.postManagement.service.PostManageService;

@RestController
public class PostJPAResource {

	private PostManageService postService;

	private PostRepository postrepository;
	
	public PostJPAResource(PostManageService postService,PostRepository postrepository) {
		this.postService = postService;
		this.postrepository = postrepository;
	}
	
	@GetMapping("/users/{username}/posts")
	public List<Post> retrievePosts(@PathVariable String username) {
		//return postService.findByUsername(username);
		return postrepository.findByUserName(username);
		
	}
	
	@GetMapping("/users/{username}/posts/{number}")
	public Post retrievePost(@PathVariable String username
			,@PathVariable int number) {
		//return postService.findById(number);
		return postrepository.findById(number).get();
	}
	@GetMapping("/users/posts")
	public List<Post> retrieveAllPosts(){
		return postrepository.findAll();
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
			,Post post,@RequestParam("ImageFile") MultipartFile multipartFile) throws IOException {
		post.setUserName(username);
		post.setPostNumber(null);
		String fileName = multipartFile.getOriginalFilename();
        post.setImageFile(fileName);
        
		return postrepository.save(post);
		
//		System.out.println(post.getPostNumber());
//		Post createdPost = postService.addPosts(username,post.getPostNumber(),post.getLocation(),post.getImageFile(),post.getDescription(),LocalDate.now());
		
}

}
