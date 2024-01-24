package com.example.demo.dto;

import com.example.demo.constant.UserEditMessage;
import com.example.demo.entity.ItemDetail;

import lombok.Data;

/**
 * アイテム詳細情報の更新結果DTO
 * @author 7d14
 */
@Data
public class DetailEditResult {
	private UserEditMessage updateMessage;

	private ItemDetail updateItemDetail;
}
