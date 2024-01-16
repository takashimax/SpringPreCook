package com.example.demo.dto;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.constant.UserEditMessage;
import com.example.demo.constant.db.ItemCategoryKind;

import lombok.Data;

@Data
public class ItemUpdateInfo {

	private String itemName;

	private MultipartFile imageUrl;

	private ItemCategoryKind itemCategoryKind;

	private UserEditMessage updateMessage;

	private LocalDateTime updateTime;

	private String updateUser;
}
