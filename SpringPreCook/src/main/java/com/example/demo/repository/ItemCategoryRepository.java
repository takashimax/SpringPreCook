package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.constant.db.ItemCategoryKind;
import com.example.demo.entity.ItemCategory;

@Repository
public interface ItemCategoryRepository extends
		JpaRepository<ItemCategory, Integer> {


	public Optional<ItemCategory> findByItemName(String itemName);
	
	public List<ItemCategory> findByItemNameLike(String itemName);
	
	public List<ItemCategory> findByItemCategoryKind(ItemCategoryKind itemCategoryKind);
	
	public List<ItemCategory> findByItemNameLikeAndItemCategoryKind(String itemName, ItemCategoryKind itemCategoryKind);
	
	@Transactional
	List<ItemCategory> deleteByItemName(String itemName);
}