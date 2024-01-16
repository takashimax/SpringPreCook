package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.constant.UserDeleteResult;
import com.example.demo.dto.ItemEditResult;
import com.example.demo.dto.ItemList;
import com.example.demo.dto.ItemSeachInfo;
import com.example.demo.dto.ItemUpdateInfo;
import com.example.demo.entity.ItemCategory;
import com.example.demo.form.ItemListCreateForm;

public interface ItemListService {

	public List<ItemList> editCategory();

	public List<ItemList> editCategoryByPram(ItemSeachInfo itemSeachInfo);

	public UserDeleteResult deleteCategoryByItemName(String itemName);

	public Optional<ItemCategory> createItemList(ItemListCreateForm itemListCreateForm);

	public Optional<ItemCategory> searchItemCategory(String itemName);

	public ItemEditResult updateCategoryInfo(ItemUpdateInfo itemUpdateInfo);
}
