package com.example.demo.form;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.constant.db.ItemCategoryKind;

import lombok.Data;

@Data
public class ItemListEditForm {
	
	private String itemName;
	
	private ItemCategoryKind itemCategoryKind;
	
	private MultipartFile imageUrl;
	
	private String selectedId;

	public ItemListEditForm clearSelectedId() {
		this.selectedId = null;

		return this;
	}
}
