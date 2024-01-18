package com.example.demo.entity;

import java.time.LocalDateTime;

import com.example.demo.constant.db.ItemCategoryKind;
import com.example.demo.entity.converter.ItemCategoryConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "item_category")
@Data
public class ItemCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "item_name")
	private String itemName;

	@Column(name = "item_genre")
	@Convert(converter = ItemCategoryConverter.class)
	private ItemCategoryKind itemCategoryKind;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "create_time")
	private LocalDateTime createTime;

	@Column(name = "update_time")
	private LocalDateTime updateTime;

	@Column(name = "update_user")
	private String updateUser;

}