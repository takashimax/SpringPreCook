package com.example.demo.form;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.ItemCategory;

import lombok.Data;

/**
 * 
 * アイテム詳細情報登録画面Form
 * @author 7d14
 * 
 */
@Data
public class ItemDetailListCreateForm {
	
	private Integer itineraryOrder;
	
	private String itineraryTitle;
	
	private String itemDetailText;
	
	private MultipartFile imageUrl;
	
	private ItemCategory id;
}
