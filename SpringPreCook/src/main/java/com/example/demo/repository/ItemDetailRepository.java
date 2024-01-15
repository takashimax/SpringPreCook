package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ItemCategory;
import com.example.demo.entity.ItemDetail;

@Repository
public interface ItemDetailRepository extends
		JpaRepository<ItemDetail, Integer> {
	List<ItemDetail> findByItemCategoryOrderByItineraryOrder(Optional<ItemCategory> itemCategoryOpt);
}