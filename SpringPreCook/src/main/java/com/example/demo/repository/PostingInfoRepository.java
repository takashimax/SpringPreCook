package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.PostingInfo;
import com.example.demo.entity.UserInfo;


public interface PostingInfoRepository extends JpaRepository<PostingInfo, Integer> {

	List<PostingInfo> findByUserInfo(UserInfo userInfo);
	
}