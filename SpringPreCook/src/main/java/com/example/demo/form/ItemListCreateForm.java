package com.example.demo.form;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.constant.db.ItemCategoryKind;

import lombok.Data;

@Data
public class ItemListCreateForm {
	
	private String itemName;
	
	private ItemCategoryKind itemCategoryKind;
	
	private MultipartFile imageUrl;
}
