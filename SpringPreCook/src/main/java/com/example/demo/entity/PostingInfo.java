package com.example.demo.entity;

import java.time.LocalDateTime;

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
@Table(name = "posting_info")
public class PostingInfo {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "login_id", referencedColumnName = "id")
	private UserInfo userInfo;

	@Column(name = "posting_title")
	private String postingTitle;

	@Column(name = "posting_text")
	private String postingText;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "create_time")
	private LocalDateTime createTime;

	@Column(name = "update_time")
	private LocalDateTime updateTime;
	
//	@OneToMany(mappedBy = "postingInfo")
//	private List<PostingMaterial> postingMaterials;
}