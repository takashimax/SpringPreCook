package com.example.demo.form;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PostingForm {

	private String postingTitle;

	private String postingText;

	private MultipartFile imageUrl;

	private Integer materialOrder;

	private Integer detailOrder;

	private MultipartFile detailImageUrl;

	private String postingDetailText;

	private List<MaterialList> materialList;

	private List<DetailList> detailLists;

	@Data
	public static class MaterialList {
		private String materialName;

		private String materialQuantity;
	}

	@Data
	public static class DetailList {

		private MultipartFile detailImageUrl;

		private String postingDetailText;
	}
}