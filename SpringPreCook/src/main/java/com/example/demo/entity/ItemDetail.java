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
@Table(name = "item_detail")
public class ItemDetail {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer detailId;

	@ManyToOne
	@JoinColumn(name = "item_category_id", referencedColumnName = "id")
	private ItemCategory itemCategory;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "itinerary_order")
	private Integer itineraryOrder;

	@Column(name = "itinerary_title")
	private String itineraryTitle;
	
	@Column(name = "item_detail_text")
	private String itemDetailText;
}