package com.example.demo.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PostingForm {
	private String itemName;
	private String userId;
	private String postingTitle;
	private String postingText;
	private MultipartFile imageUrl;
}