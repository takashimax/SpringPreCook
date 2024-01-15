package com.example.demo.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(name = "item_category", uniqueConstraints = {
		@UniqueConstraint(columnNames = "item_name") })
@Data
public class ItemCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "item_name")
	private String itemName;

	@Column(name = "item_genre")
	private Integer itemGenre;

	@Column(name = "image_url")
	private String imageUrl;

	@CreationTimestamp
	@Column(name = "create_time")
	private LocalDateTime createTime;

	@UpdateTimestamp
	@Column(name = "update_time")
	private LocalDateTime updateTime;
	
	@ManyToOne
	@JoinColumn(name = "update_user" ,referencedColumnName = "login_id")
	private UserInfo userInfo; 
	
}