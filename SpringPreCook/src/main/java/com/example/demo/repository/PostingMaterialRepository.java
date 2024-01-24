package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.PostingInfo;
import com.example.demo.entity.PostingMaterial;

/**
 * 投稿食材テーブルのリポジトリークラス
 * @author 7d14
 */
@Repository
public interface PostingMaterialRepository extends JpaRepository<PostingMaterial, Integer> {
	
	/**
	 * PostingIdの一致検索
	 * @param postingInfo
	 * @return
	 */
	List<PostingMaterial> findByPostingInfo(PostingInfo postingInfo);
	
	/**
	 * 材料名の部分一致検索
	 * @param materialName
	 * @return
	 */
	List<PostingMaterial> findByMaterialNameLike(String materialName);
	
	/**
	 * PostingIDに一致するレコードの削除
	 * @param postingInfo
	 */
	@Transactional
	void deleteByPostingInfo(PostingInfo postingInfo);
}
