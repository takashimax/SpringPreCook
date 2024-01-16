package com.example.demo.form;

import com.example.demo.constant.db.ItemCategoryKind;

import lombok.Data;

@Data
public class ItemListForm {

	private String itemName;

	private ItemCategoryKind itemCategoryKind;

	private String selectedId;
	
	public ItemListForm clearSelectedId() {
		this.selectedId = null;

		return this;
	}
}
