package com.example.demo.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PrecookEditForm {
	private Integer itemId;
	private Integer itemGenre;
	private String itemName;
	private MultipartFile itemCategoryImageUrl;
	private Integer itineraryOrder;
	private String itineraryTitle;
	private MultipartFile itemDetailImageUrl;
	private String itemDetailText;
}
