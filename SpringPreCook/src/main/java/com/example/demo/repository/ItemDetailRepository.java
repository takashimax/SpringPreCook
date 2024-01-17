package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ItemCategory;
import com.example.demo.entity.ItemDetail;

public interface ItemDetailRepository extends
		JpaRepository<ItemDetail, Integer> {

	List<ItemDetail> findByItemCategoryOrderByItineraryOrder(Optional<ItemCategory> itemCategoryOpt);

}