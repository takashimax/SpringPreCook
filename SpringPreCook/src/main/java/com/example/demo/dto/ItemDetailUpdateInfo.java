package com.example.demo.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ItemDetailUpdateInfo {
	
	private Integer itineraryOrder;
	
	private String itineraryTitle;
	
	private String itemDetailText;
	
	private MultipartFile imageUrl;
	
	private Integer detailId;
}
