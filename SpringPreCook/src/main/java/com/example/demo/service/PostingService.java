package com.example.demo.service;

import java.io.IOException;

import com.example.demo.form.PostingForm;

public interface PostingService {
	/**
	 * ユーザー登録
	 * 
	 * @param form フォーム情報
	 * @throws IOException
	 */
	public void posting(PostingForm form) throws IOException;
}
