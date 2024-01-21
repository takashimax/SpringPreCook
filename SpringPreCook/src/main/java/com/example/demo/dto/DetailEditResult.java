package com.example.demo.dto;

import com.example.demo.constant.UserEditMessage;
import com.example.demo.entity.ItemDetail;

import lombok.Data;

@Data
public class DetailEditResult {
	private UserEditMessage updateMessage;

	private ItemDetail updateItemDetail;
}
