package com.example.demo.form;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.constant.db.ItemCategoryKind;

import lombok.Data;

/**
 * カテゴリー編集画面更新Form
 * @author 7d14
 */
@Data
public class ItemListEditForm {
	
	private Integer id;
	
	private String itemName;
	
	private ItemCategoryKind itemCategoryKind;
	
	private MultipartFile imageUrl;
	
	private String itineraryTitle;
	
	private Integer itineraryOrder;
	
	private Integer selectedDetailId;

	public ItemListEditForm clearSelectedDetalId() {
		this.selectedDetailId = null;

		return this;
	}
}
