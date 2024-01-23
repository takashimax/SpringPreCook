package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.PostingInfo;
import com.example.demo.entity.PostingMaterial;

@Repository
public interface PostingMaterialRepository extends JpaRepository<PostingMaterial, Integer> {

	List<PostingMaterial> findByPostingInfo(PostingInfo postingInfo);
	
	List<PostingMaterial> findByMaterialNameLike(String materialName);
	
	@Transactional
	void deleteByPostingInfo(PostingInfo postingInfo);
}
