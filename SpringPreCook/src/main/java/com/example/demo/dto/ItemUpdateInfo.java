package com.example.demo.dto;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.constant.db.ItemCategoryKind;

import lombok.Data;


/**
 * カテゴリー情報更新DTO
 * @author 7d14
 */
@Data
public class ItemUpdateInfo {

	private String itemName;

	private MultipartFile imageUrl;

	private ItemCategoryKind itemCategoryKind;
	
	private String updateUserId;
}
