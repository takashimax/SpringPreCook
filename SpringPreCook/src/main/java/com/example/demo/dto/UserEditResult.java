package com.example.demo.dto;

import com.example.demo.constant.UserEditMessage;
import com.example.demo.entity.UserInfo;

import lombok.Data;

/**
 * ユーザー編集結果DTOクラス
 * 
 * @author 7d14
 */
@Data
public class UserEditResult {

	/** ユーザー更新結果 */
	private UserInfo updateUserInfo;

	/** ユーザー更新結果メッセージEnum */
	private UserEditMessage updateMessage;
}