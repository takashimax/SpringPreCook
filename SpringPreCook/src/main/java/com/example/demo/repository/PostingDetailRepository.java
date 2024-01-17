package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.PostingDetail;

public interface PostingDetailRepository extends JpaRepository<PostingDetail, Integer> {

}
