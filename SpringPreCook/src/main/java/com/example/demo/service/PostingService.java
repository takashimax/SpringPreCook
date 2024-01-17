package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.PostingInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.PostingForm;

public interface PostingService {
	/**
	 * ユーザー登録
	 * 
	 * @param form フォーム情報
	 * @throws IOException
	 */
	
	public List<PostingInfo> findPost(UserInfo userInfo);
	
	public Optional<PostingInfo> createPost(PostingForm postingForm);
}
