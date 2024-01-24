package com.example.demo.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * アイテム詳細情報編集画面Form
 * @author 7d14
 * 
 */
@Data
public class ItemDetailListEditForm {
	
	private String itineraryTitle;
	
	private Integer itineraryOrder;
	
	private String itemDetailText;
	
	private MultipartFile imageUrl;
	
	private Integer selectedDetailId;

	public ItemDetailListEditForm clearSelectedDetailId() {
		this.selectedDetailId = null;

		return this;
	}
}
