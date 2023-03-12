package com.kopuz.questApp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kopuz.questApp.entities.User;
import com.kopuz.questApp.requests.UserRequest;
import com.kopuz.questApp.security.JwtTokenProvider;
import com.kopuz.questApp.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private AuthenticationManager authenticationManager;
	private JwtTokenProvider tokenProvider;
	private UserService userService;
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	public String login(@RequestBody UserRequest loginRequest) {
		
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
		
		Authentication auth = authenticationManager.authenticate(authToken);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		String jwtToken = tokenProvider.generateJwtToken(auth);
		
		return "Bearer " + jwtToken;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserRequest registerRequest)
	{
		if(userService.getUserByUserName(registerRequest.getUserName()) != null){
			return new ResponseEntity<>("Username is already exist", HttpStatus.BAD_REQUEST);
		}
		
		User user = new User();
		user.setUserName(registerRequest.getUserName());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		
		userService.saveUser(user);
		
		return new ResponseEntity<>("User successfully created.", HttpStatus.CREATED);
		
	}
}
