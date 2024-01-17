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
@Table(name = "posting_detail")
@Data
public class PostingDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "posting_id" ,referencedColumnName = "id")
	private PostingInfo postingInfo; 
	
	@Column(name = "detail_order")
	private Integer detailOrder;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "posting_detail_text")
	private String postingDetailText;
}
