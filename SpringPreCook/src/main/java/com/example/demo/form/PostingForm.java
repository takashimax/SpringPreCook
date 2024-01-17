package com.example.demo.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PostingForm {
	
	private String postingTitle;
	
	private String postingText;
	
	private MultipartFile imageUrl;
	
	private Integer materialOrder;
	
	private String materialName;
	
	private String materialQuantity;
	
	private Integer detailOrder;
	
	private MultipartFile detailImageUrl;
	
	private String postingDetailText;
}