package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.ItemCategory;
import com.example.demo.entity.ItemDetail;

@Repository
public interface ItemDetailRepository extends
		JpaRepository<ItemDetail, Integer> {

	List<ItemDetail> findByItemCategoryOrderByItineraryOrder(Optional<ItemCategory> itemCategoryOpt);
	
	List<ItemDetail> findByItemCategory(ItemCategory itemCategory);
	
	List<ItemDetail> findByItineraryTitleLikeAndItineraryOrder(String itineraryTitle, Integer itineraryOrder);
	
	List<ItemDetail> findByItineraryTitleLike(String itineraryTitle);
	
	
	List<ItemDetail> findByItineraryOrderOrderByItineraryOrder(Integer itineraryOrder);
	
	boolean existsByItemCategory(ItemCategory itemCategory);
	
	@Transactional
	void deleteByItemCategory(ItemCategory itemCategory);

	Optional<ItemCategory> findByItineraryTitle(String itineraryTitle);
	
}