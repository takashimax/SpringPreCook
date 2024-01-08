package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PostingInfo;
import com.example.demo.entity.UserInfo;

@Repository
public interface PostingRepository extends JpaRepository<PostingInfo, Integer> {
	
	List<PostingInfo> findByUserInfo(UserInfo userInfo);
}