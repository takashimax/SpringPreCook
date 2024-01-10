package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ItemCategoryInfo;

@Repository
public interface ItemCategoryRepository extends
		JpaRepository<ItemCategoryInfo, Integer> {
	public List<ItemCategoryInfo> findByItemGenre(Integer itemGenre);

	public Optional<ItemCategoryInfo> findByItemName(String itemCategoryInfo);
}