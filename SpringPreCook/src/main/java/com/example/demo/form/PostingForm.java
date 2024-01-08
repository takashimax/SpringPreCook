package com.example.demo.form;

import lombok.Data;

@Data
public class PostingForm {
	private String userId;
	private String postingTitle;
	private String postingText;
	private String imageUrl;
}