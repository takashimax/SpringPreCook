package com.example.demo.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;


/**
 * アイテム詳細情報更新DTO
 * @author 7d14
 */
@Data
public class ItemDetailUpdateInfo {
	
	private Integer itineraryOrder;
	
	private String itineraryTitle;
	
	private String itemDetailText;
	
	private MultipartFile imageUrl;
	
	private Integer detailId;
}
