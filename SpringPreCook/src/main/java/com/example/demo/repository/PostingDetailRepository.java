package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.PostingDetail;
import com.example.demo.entity.PostingInfo;


public interface PostingDetailRepository extends JpaRepository<PostingDetail, Integer> {
	
	Optional<PostingDetail> findByPostingInfo(PostingInfo postingInfo);
}
