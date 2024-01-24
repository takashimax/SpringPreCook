package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.constant.db.ItemCategoryKind;
import com.example.demo.entity.ItemCategory;

/**
 * アイテムカテゴリー情報テーブルリポジトリークラス
 * 	
 * @author 7d14
 * 
 */
@Repository
public interface ItemCategoryRepository extends
		JpaRepository<ItemCategory, Integer> {

	
	/**
	 * 
	 * @param itemName
	 * @return
	 */
	public Optional<ItemCategory> findByItemName(String itemName);
	
	/**
	 * アイテム名の部分一致検索
	 * @param itemName
	 * @return
	 */
	public List<ItemCategory> findByItemNameLike(String itemName);
	
	/**
	 * カテゴリー名と一致検索
	 * @param itemCategoryKind
	 * @return
	 */
	public List<ItemCategory> findByItemCategoryKind(ItemCategoryKind itemCategoryKind);

	/**
	 * アイテム名、カテゴリー名と一致検索
	 * @param itemName
	 * @param itemCategoryKind
	 * @return
	 */
	public List<ItemCategory> findByItemNameLikeAndItemCategoryKind(String itemName, ItemCategoryKind itemCategoryKind);

	/**
	 * アイテム名と一致するレコードの削除
	 * @param itemName
	 * @return
	 */
	@Transactional
	List<ItemCategory> deleteByItemName(String itemName);
}