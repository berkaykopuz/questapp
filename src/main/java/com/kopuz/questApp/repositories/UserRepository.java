package com.kopuz.questApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kopuz.questApp.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
