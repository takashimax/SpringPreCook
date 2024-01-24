package com.example.demo.form;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.constant.db.ItemCategoryKind;

import lombok.Data;

/**
 * @author 7d14
 * 
 */
@Data
public class ItemListForm {
	
	private Integer id;
	
	private String itemName;
	
	private ItemCategoryKind itemCategoryKind;
	
	private MultipartFile imageUrl;
	
	private String selectedId;

	public ItemListForm clearSelectedId() {
		this.selectedId = null;

		return this;
	}

}
