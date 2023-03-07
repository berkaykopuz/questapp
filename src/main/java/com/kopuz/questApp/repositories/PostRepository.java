package com.kopuz.questApp.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kopuz.questApp.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByUserId(Long userId);
	
	
}
