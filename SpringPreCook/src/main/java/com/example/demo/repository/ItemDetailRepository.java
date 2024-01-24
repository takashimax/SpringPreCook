package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.ItemCategory;
import com.example.demo.entity.ItemDetail;

/**
 * アイテム詳細テーブルリポジトリークラス
 * @author 7d14
 * 
 */
@Repository
public interface ItemDetailRepository extends
		JpaRepository<ItemDetail, Integer> {
	
	/**
	 * カテゴリーIDと一致した情報をItineraryOrder(作業順)を降順取得
	 * @param itemCategoryOpt
	 * @return
	 */
	List<ItemDetail> findByItemCategoryOrderByItineraryOrder(Optional<ItemCategory> itemCategoryOpt);
	
	/**
	 * カテゴリーIDに一致検索
	 * @param itemCategory
	 * @return
	 */
	List<ItemDetail> findByItemCategory(ItemCategory itemCategory);
		
	/**
	 * 行程名(ItineraryTitle)の部分一致検索をItineraryOrder(作業順)を降順で取得
	 * @param itineraryTitle
	 * @param itineraryOrder
	 * @return
	 */
	List<ItemDetail> findByItineraryTitleLikeAndItineraryOrder(String itineraryTitle, Integer itineraryOrder);
	
	/**
	 * 行程名の部分一致検索
	 * @param itineraryTitle
	 * @return
	 */
	List<ItemDetail> findByItineraryTitleLike(String itineraryTitle);
	
	/**
	 * 行程順検索結果を降順取得
	 * @param itineraryOrder
	 * @return
	 */
	List<ItemDetail> findByItineraryOrderOrderByItineraryOrder(Integer itineraryOrder);
	
	/**
	 * カテゴリーIDの存在確認
	 * @param itemCategory
	 * @return
	 */
	boolean existsByItemCategory(ItemCategory itemCategory);
	
	/**
	 * カテゴリーIDに一致するレコードの削除
	 * @param itemCategory
	 */
	@Transactional
	void deleteByItemCategory(ItemCategory itemCategory);
	
	Optional<ItemCategory> findByItineraryTitle(String itineraryTitle);
	
}