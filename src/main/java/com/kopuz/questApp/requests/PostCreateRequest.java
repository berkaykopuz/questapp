package com.kopuz.questApp.requests;

import lombok.Data;

@Data
public class PostCreateRequest {
	private Long id;
	private String title;
	private String text;
	private Long userid;
}
