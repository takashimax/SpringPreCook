package com.example.demo.dto;

import lombok.Data;

/**
 * 投稿内容DTO
 * @author 7d14
 */
@Data
public class PostingResult {
	
	private String postingTitle;
	
	private String postingText;
	
	private String imageUrl;
	
	private Integer materialOrder;
	
	private String materialName;
	
	private String materialQuantity;
	
	private Integer detailOrder;
	
	private String detailImageUrl;
	
	private String postingDetailText;
}
