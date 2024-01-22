package com.example.demo.dto;

import com.example.demo.entity.PostingInfo;

import lombok.Data;

@Data
public class PostingMaterialList {
	private Integer detailId;

	private PostingInfo postingInfo;

	private Integer materialOrder;

	private String materialName;

	private String materialQuantity;
}
