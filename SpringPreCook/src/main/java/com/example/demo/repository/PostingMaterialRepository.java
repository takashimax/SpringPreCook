package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PostingInfo;
import com.example.demo.entity.PostingMaterial;

@Repository
public interface PostingMaterialRepository extends JpaRepository<PostingMaterial, Integer> {

	public Optional<PostingMaterial> findByPostingInfo(PostingInfo postingInfo);
}
