package com.example.demo.form;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.ItemCategory;

import lombok.Data;

@Data
public class ItemDetailListCreateForm {
	
	private Integer itineraryOrder;
	
	private String itineraryTitle;
	
	private String itemDetailText;
	
	private MultipartFile imageUrl;
	
	private ItemCategory id;
}
