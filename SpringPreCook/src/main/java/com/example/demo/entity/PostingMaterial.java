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
@Table(name = "posting_material")
@Data
public class PostingMaterial {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer detailId;

	@ManyToOne
    @JoinColumn(name = "posting_id",referencedColumnName = "id")
    private PostingInfo postingInfo;

	@Column(name = "material_order")
	private Integer materialOrder;

	@Column(name = "material_name")
	private String materialName;

	@Column(name = "material_quantity")
	private String materialQuantity;
}
