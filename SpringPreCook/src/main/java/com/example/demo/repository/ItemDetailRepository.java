package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ItemCategoryInfo;
import com.example.demo.entity.ItemDetailInfo;

@Repository
public interface ItemDetailRepository extends
		JpaRepository<ItemDetailInfo, Integer> {
	List<ItemDetailInfo> findByItemCategoryInfo(Optional<ItemCategoryInfo> itemCategoryOpt);
}