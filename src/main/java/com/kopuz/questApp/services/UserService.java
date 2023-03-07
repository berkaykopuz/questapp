package com.kopuz.questApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kopuz.questApp.entities.User;
import com.kopuz.questApp.repositories.UserRepository;

@Service
public class UserService { //Business mantiginin dondugu katmandir. Repositoryden aldigina logic kurallari ekler.
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}

	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public User getUser(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

	public User updateUser(Long userId, User newUser) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			User foundUser = user.get();
			foundUser.setUserName(newUser.getUserName());
			foundUser.setPassword(newUser.getPassword());
			userRepository.save(foundUser); 
			return foundUser;
		}
		else {
			return null;
		}
	}

	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
		
	}

	
	
}
