package com.example.demo.dto;

import com.example.demo.constant.UserEditMessage;
import com.example.demo.entity.ItemCategory;

import lombok.Data;

/**
 * アイテムカテゴリー編集DTO
 * @author 7d14
 */
@Data
public class ItemEditResult {
	private UserEditMessage updateMessage;
	
	private ItemCategory updateItemCategory;
}
