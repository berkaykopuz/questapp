package com.kopuz.questApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kopuz.questApp.entities.Post;
import com.kopuz.questApp.entities.User;
import com.kopuz.questApp.repositories.PostRepository;
import com.kopuz.questApp.requests.PostCreateRequest;
import com.kopuz.questApp.requests.PostUpdateRequest;

@Service
public class PostService {
	private PostRepository postRepository;
	private UserService userService;
	
	
	public PostService(PostRepository postRepository,UserService userService) {
		
		this.postRepository = postRepository;
		this.userService = userService;
	}



	public List<Post> getAllPosts(Optional<Long> userId) {
		if(userId.isPresent()) {
			return postRepository.findByUserId(userId.get());
		}
		return postRepository.findAll();
	}



	public Post getPost(Long postId) {
		return postRepository.findById(postId).orElse(null);
	}



	public Post createPost(PostCreateRequest newPostRequest) {
		User user = userService.getUser(newPostRequest.getUserid());
		
		if(user==null) {
			return null;
		}
		
		Post toSave = new Post();
		toSave.setId(newPostRequest.getId());
		toSave.setTitle(newPostRequest.getTitle());
		toSave.setText(newPostRequest.getText());
		toSave.setUser(user);
		
		return postRepository.save(toSave);
	}



	public void deletePostbyId(Long postId) {
		
		postRepository.deleteById(postId);
		
	}



	public Post updatePostbyId(Long postId,PostUpdateRequest postRequest) {
		
		Optional<Post> post = postRepository.findById(postId);
		
		if(post.isPresent()) {
			Post toUpdate = post.get();
			toUpdate.setText(postRequest.getText());
			toUpdate.setTitle(postRequest.getTitle());
			postRepository.save(toUpdate);
			
			return toUpdate;
		}
		
		return null;
	}

}
