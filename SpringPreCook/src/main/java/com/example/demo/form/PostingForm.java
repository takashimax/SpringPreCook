package com.example.demo.form;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PostingForm {

	private Integer id;
	
	@NotBlank(message = "件名を入力してください。")
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

	private String selectedId;

	@Data
	public static class DetailList {

		private MultipartFile detailImageUrl;

		private String postingDetailText;
	}

	public PostingForm clearSelectedId() {
		this.selectedId = null;

		return this;
	}
}