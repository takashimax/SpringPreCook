package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ItemList {
	
	private Integer id;
	
	private String itemName;
	
	private String itemCategory;
	
	private String imageUrl;
	
	private LocalDateTime createTime;

	private LocalDateTime updateTime;

	private String updateUser ;
}
