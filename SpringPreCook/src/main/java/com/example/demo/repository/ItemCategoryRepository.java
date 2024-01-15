package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ItemCategory;

@Repository
public interface ItemCategoryRepository extends
		JpaRepository<ItemCategory, Integer> {
	public List<ItemCategory> findByItemGenre(Integer itemGenre);

	public Optional<ItemCategory> findByItemName(String itemCategory);

}