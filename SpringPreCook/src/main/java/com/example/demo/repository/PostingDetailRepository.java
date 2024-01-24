package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.PostingDetail;
import com.example.demo.entity.PostingInfo;

/**
 * 投稿詳細情報テーブルのリポジトリークラス
 * @author 7d14
 */
public interface PostingDetailRepository extends JpaRepository<PostingDetail, Integer> {
	
	/**
	 * PostingIDの一致検索
	 * @param postingInfo
	 * @return
	 */
	List<PostingDetail> findByPostingInfo(PostingInfo postingInfo);
	
	/**
	 * PostingIDに一致するレコードの削除
	 * @param postingInfo
	 */
	@Transactional
	void deleteByPostingInfo(PostingInfo postingInfo);

}
