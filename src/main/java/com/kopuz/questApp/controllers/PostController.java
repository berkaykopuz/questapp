package com.kopuz.questApp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kopuz.questApp.entities.Post;
import com.kopuz.questApp.requests.PostCreateRequest;
import com.kopuz.questApp.requests.PostUpdateRequest;
import com.kopuz.questApp.services.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {
	private PostService postService;

	public PostController(PostService postService) {
		
		this.postService = postService;
	}
	
	@GetMapping
	public List<Post> getAllPosts(@RequestParam Optional<Long> userId){
		return postService.getAllPosts(userId);
	}
	
	@GetMapping("/{postId}")
	public Post getPost(@PathVariable Long postId) {
		return postService.getPost(postId);
	}
	
	@PostMapping
	public Post createPost(@RequestBody PostCreateRequest newRequestPost) {
		return postService.createPost(newRequestPost);
	}
	
	@PutMapping("/{postId}")
	public Post updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest postRequest) {
		return postService.updatePostbyId(postId,postRequest);
	}
	@DeleteMapping("/{postId}")
	public void deletePost(@PathVariable Long postId) {
		postService.deletePostbyId(postId);
	}
	
}
