//package com.cyanApp1.postManagement.resource;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.cyanApp1.postManagement.model.Post;
//import com.cyanApp1.postManagement.service.PostManageService;
//
////@RestController
//public class PostResource {
//
//	private PostManageService postService;
//
//	public PostResource(PostManageService postService) {
//		this.postService = postService;
//	}
//	
//	@GetMapping("/users/{username}/posts")
//	public List<Post> retrievePosts(@PathVariable String username) {
//		return postService.findByUsername(username);
//	}
//	
//	@GetMapping("/users/{username}/posts/{number}")
//	public Post retrievePost(@PathVariable String username
//			,@PathVariable int number) {
//		return postService.findById(number);
//	}
//	
//	@DeleteMapping("/users/{username}/posts/{number}")
//	public ResponseEntity<Void> deletePost(@PathVariable String username
//			,@PathVariable int number){
//		postService.deleteById(number);
//		return ResponseEntity.noContent().build();
//	}
//	
//	@PutMapping("/users/{username}/posts/{number}")
//	public Post updatePost(@PathVariable String username
//							,@PathVariable int number, @RequestBody Post post) {
//		System.out.println("POST NUMBER RESOURCE " + number);
//		post.setPostNumber(number);
//		System.out.println("POST NUMBER RESOURCE 2 "+ post.getPostNumber());
//		postService.updatePost(post);
//		return post;
//	}
//	
//	@PostMapping("/users/{username}/posts")
//	public Post createPost(@PathVariable String username
//			, @RequestBody Post post) {
//		
//		//Post createdPost = postService.addPosts(username,post.getPostNumber(),post.getLocation(),post.getImageFile(),post.getDescription(),LocalDate.now());
//		return createdPost;
//}

//}
