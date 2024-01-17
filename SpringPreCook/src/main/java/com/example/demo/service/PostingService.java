package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.PostingDetail;
import com.example.demo.entity.PostingInfo;
import com.example.demo.entity.PostingMaterial;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.PostingForm;

public interface PostingService {
	/**
	 * ユーザー登録
	 * 
	 * @param form フォーム情報
	 * @throws IOException
	 */
	public Optional<PostingInfo> findPostingInfos(Integer id);
	
	public List<PostingDetail> findPostingDetail(PostingInfo postingInfo);
	
	public List<PostingMaterial> findPostingMaterial(PostingInfo postingInfo);

	public List<PostingInfo> findPost(UserInfo userInfo);

	public Optional<PostingInfo> createPostInfo(PostingForm postingForm,UserInfo userInfo);

	public Optional<PostingMaterial> createPostMaterial(PostingForm postingForm, PostingInfo postingInfo);

	public Optional<PostingDetail> createPostDetail(PostingForm postingForm, PostingInfo postingInfo);
	
	public void createPostingResult(PostingForm postingForm,UserInfo userInfo);
}
