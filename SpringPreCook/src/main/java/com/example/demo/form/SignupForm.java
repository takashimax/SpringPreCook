package com.example.demo.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import lombok.Data;

/**
 * ユーザー登録画面Formクラス
 * 
 * @author 7d14
 *
 */
@Data
public class SignupForm {

	/** ログインID */
	@Length(min = 8, max = 20)
	@Email
	private String loginId;

	/** パスワード */
	@Length(min = 8, max = 20)
	private String password;
}