package com.kopuz.questApp.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kopuz.questApp.entities.Comment;
import com.kopuz.questApp.entities.Post;
import com.kopuz.questApp.entities.User;
import com.kopuz.questApp.repositories.CommentRepository;
import com.kopuz.questApp.requests.CommentCreateRequest;
import com.kopuz.questApp.requests.CommentUpdateRequest;
import com.kopuz.questApp.responses.CommentResponse;

@Service
public class CommentService {

	private CommentRepository commentRepository;
	private UserService userService;
	private PostService postService;

	public CommentService(CommentRepository commentRepository, UserService userService, 
			PostService postService) {
		this.commentRepository = commentRepository;
		this.userService = userService;
		this.postService = postService;
	}

	public List<CommentResponse> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
		List<Comment> comments;
		if(userId.isPresent() && postId.isPresent()) {
			comments = commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
		}else if(userId.isPresent()) {
			comments = commentRepository.findByUserId(userId.get());
		}else if(postId.isPresent()) {
			comments = commentRepository.findByPostId(postId.get());
		}else
			comments = commentRepository.findAll();
		return comments.stream().map(comment -> new CommentResponse(comment)).collect(Collectors.toList());
	}

	public Comment getOneCommentById(Long commentId) {
		return commentRepository.findById(commentId).orElse(null);
	}

	public Comment createOneComment(CommentCreateRequest request) {
		User user = userService.getUser(request.getUserId());
		Post post = postService.getPost(request.getPostId());
		if(user != null && post != null) {
			Comment commentToSave = new Comment();
			commentToSave.setId(request.getId());
			commentToSave.setPost(post);
			commentToSave.setUser(user);
			commentToSave.setText(request.getText());
			commentToSave.setCreateDate(new Date());
			return commentRepository.save(commentToSave);
		}else		
			return null;
	}

	public Comment updateOneCommentById(Long commentId, CommentUpdateRequest request) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		if(comment.isPresent()) {
			Comment commentToUpdate = comment.get();
			commentToUpdate.setText(request.getText());
			return commentRepository.save(commentToUpdate);
		}else
			return null;
	}

	public void deleteOneCommentById(Long commentId) {
		commentRepository.deleteById(commentId);
	}
	
	
}