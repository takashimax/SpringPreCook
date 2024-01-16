package com.example.demo.dto;

import com.example.demo.constant.db.ItemCategoryKind;

import lombok.Data;

@Data
public class ItemSeachInfo {

	private String itemName;

	private ItemCategoryKind itemCategoryKind;


}
