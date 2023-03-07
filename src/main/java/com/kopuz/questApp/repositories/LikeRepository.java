package com.kopuz.questApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kopuz.questApp.entities.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

}
