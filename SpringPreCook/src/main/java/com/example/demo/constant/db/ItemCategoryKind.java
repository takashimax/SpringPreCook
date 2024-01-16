package com.example.demo.constant.db;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemCategoryKind {
	
	VEGETABLE("1","野菜"),
	
	MEAT("2","肉類"),
	
	SEAFOOD("3","魚介類"),
	
	UNKNOWN("","不明な内容");
	
	private String code;
	
	private String displayValue;
	
	public static ItemCategoryKind from(String code) {
		return Arrays.stream(ItemCategoryKind.values())
				.filter(itemCategoryKind -> itemCategoryKind.getCode().equals(code))
				.findFirst()
				.orElse(UNKNOWN);
	}
}
