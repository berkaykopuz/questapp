package com.kopuz.questApp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;



@Entity
@Table(name="user")
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)	
	private long id;
	
	@Column(nullable=false, unique=true)
	private String userName;
	
	@Column(nullable=false)
	private String password;
	
	
	
	
}
