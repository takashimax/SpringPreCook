package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ItemList;
import com.example.demo.entity.ItemCategory;

public interface ItemListService {
	
	public List<ItemList> editCategory();
	
	public List<ItemCategory> editCategoryByPram();
	
	public void deleteCategoryByItemName();
	
	
}
