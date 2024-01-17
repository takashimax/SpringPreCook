package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.PostingInfo;
import com.example.demo.entity.PostingMaterial;


public interface PostingMaterialRepository extends JpaRepository<PostingMaterial, Integer> {
	
	public List<PostingMaterial> findByPostingInfo(PostingInfo postingInfo);
}
