package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.PostingInfo;
import com.example.demo.entity.UserInfo;

/**
 * 投稿情報テーブルのリポジトリークラス
 * @author 7d14
 */
public interface PostingInfoRepository extends JpaRepository<PostingInfo, Integer> {
	
	/**
	 * ID検索
	 * @param userInfo
	 * @return
	 */
	List<PostingInfo> findByUserInfo(UserInfo userInfo);
	
}