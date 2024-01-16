package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ItemList;
import com.example.demo.dto.ItemSeachInfo;
import com.example.demo.entity.ItemCategory;
import com.example.demo.repository.ItemCategoryRepository;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemListServiceImpl implements ItemListService {

	private final ItemCategoryRepository itemCategoryRepository;

	private final Mapper mapper;

	@Override
	public List<ItemList> editCategory() {
		return toItemCategories(itemCategoryRepository.findAll());
	}
	
	public List<ItemList> editItemListByParam(ItemSeachInfo itemSeachInfo) {
		return toItemCategories(findItemInfoByParam(itemSeachInfo));
	}

	@Override
	public List<ItemCategory> editCategoryByPram() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void deleteCategoryByItemName() {
		// TODO 自動生成されたメソッド・スタブ

	}

	private List<ItemCategory> findItemInfoByParam(ItemSeachInfo itemSeachInfo) {
		String itemNameParam = AppUtil.addWildcard(itemSeachInfo.getItemName());

		if (itemSeachInfo.getItemCategoryKind() != null && itemSeachInfo.getItemName() != null) {
			return itemCategoryRepository.findByItemNameLikeAndItemCategoryKind(itemNameParam,
					itemSeachInfo.getItemCategoryKind());
		} else if (itemSeachInfo.getItemCategoryKind() != null) {
			return itemCategoryRepository.findByItemCategoryKind(itemSeachInfo.getItemCategoryKind());
		}  else {
			return itemCategoryRepository.findByItemNameLike(itemNameParam);
		}
	}

	private List<ItemList> toItemCategories(List<ItemCategory> itemCategories) {
		var itemList = new ArrayList<ItemList>();
		for (ItemCategory itemCategory : itemCategories) {
			var itemCategoryList = mapper.map(itemCategory, ItemList.class);
			((ItemList) itemCategoryList).setItemCategory(itemCategory.getItemCategoryKind().getDisplayValue());
			itemList.add((ItemList) itemCategoryList);
		}

		return itemList;

	}
}
