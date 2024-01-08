package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "items_detail")
public class ItemDetailInfo {

	@Id
	@Column(name = "detail_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer detailId;

	@ManyToOne(optional = true)
	@JoinColumn(name = "item_id", referencedColumnName = "item_id", insertable = false, updatable = false)
	private ItemCategoryInfo itemCategoryInfo;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "itinerary_order")
	private String itineraryOrder;

	@Column(name = "itinerary_title")
	private String itineraryTitle;

	private String text;
}