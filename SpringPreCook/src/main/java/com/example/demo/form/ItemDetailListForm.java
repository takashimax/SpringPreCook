package com.example.demo.form;

import lombok.Data;

/**
 * @author 7d14
 */
@Data
public class ItemDetailListForm {
	
	private String itineraryTitle;
	
	private Integer itineraryOrder;
	
	private String selectedDetailId;

	public ItemDetailListForm clearSelectedDetailId() {
		this.selectedDetailId = null;

		return this;
	}
}
