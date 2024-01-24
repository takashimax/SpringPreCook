package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.example.demo.constant.DeleteResult;
import com.example.demo.entity.PostingDetail;
import com.example.demo.entity.PostingInfo;
import com.example.demo.entity.PostingMaterial;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.PostingForm;

/**
 * 投稿に関するServiceインターフェース
 * @author 7d14
 */
public interface PostingService {
	
	/**
	 * 投稿の検索結果を返却。
	 * @param id
	 * @return
	 */
	public Optional<PostingInfo> findPostingInfos(Integer id);
	
	/**
	 * 投稿詳細の検索結果を返却。
	 * @param postingInfo
	 * @return
	 */
	public List<PostingDetail> findPostingDetail(PostingInfo postingInfo);
	
	/**
	 * 投稿材料の検索結果を返却。
	 * @param postingInfo
	 * @return
	 */
	public List<PostingMaterial> findPostingMaterial(PostingInfo postingInfo);
	
	/**
	 * ユーザーIDに基づく投稿概要の検索結果を返却。
	 * @param userInfo
	 * @return
	 */
	public List<PostingInfo> findPost(UserInfo userInfo);
	
	/**
	 * 投稿Formの入力内容を投稿テーブルに新規登録。
	 * @param postingForm
	 * @param userInfo
	 * @return
	 */
	public Optional<PostingInfo> createPostInfo(PostingForm postingForm,UserInfo userInfo);
	
	/**
	 * 投稿Formの入力内容を材料テーブルに新規登録。
	 * @param postingForm
	 * @param postingInfo
	 */
	public void createPostMaterial(PostingForm postingForm, PostingInfo postingInfo);
	
	/**
	 * 投稿Formの入力内容を詳細テーブルに新規登録。
	 * @param postingForm
	 * @param postingInfo
	 */
	public void createPostDetail(PostingForm postingForm, PostingInfo postingInfo);
	
	/**
	 * 投稿概要テーブル、材料テーブル、詳細テーブルの新規登録。
	 * @param postingForm
	 * @param userInfo
	 */
	public void createPostingResult(PostingForm postingForm,UserInfo userInfo);
	
	/**
	 * 材料テーブルから材料名に部分一致検索した結果を返却。
	 * @param postingName
	 * @return
	 */
	List<PostingMaterial> findPostingMaterialLike(String postingName);
	
	/**
	 * 投稿テーブル、材料テーブル、詳細テーブルの削除結果を表示。
	 * @param id
	 * @return
	 * @throws IOException
	 */
	DeleteResult deletePosting(Integer id) throws IOException;
}
