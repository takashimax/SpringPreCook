package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.entity.ItemCategory;
import com.example.demo.entity.ItemDetail;
import com.example.demo.repository.ItemCategoryRepository;
import com.example.demo.repository.ItemDetailRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PrecookController {

	private final ItemCategoryRepository itemCategoryRepository;
	private final ItemDetailRepository itemDetailRepository;

	@GetMapping(UrlConst.PRECOOK + "/{itemName}")
	public String view(@PathVariable(name = "itemName") String itemName, Model model) {
		/*
		 * アイテムカテゴリーの品目名の取得
		 */
		Optional<ItemCategory> itemCategoryOpt = itemCategoryRepository.findByItemName(itemName);
		model.addAttribute("itemCategoryOpt", itemCategoryOpt);
		/*
		 * アイテムカテゴリーの品目名からdetailテーブルの情報を取得
		 */
		List<ItemDetail> itemDetailList = itemDetailRepository
				.findByItemCategoryOrderByItineraryOrder(itemCategoryOpt);
		model.addAttribute("itemDetailList", itemDetailList);
		return ViewNameConst.PRECOOK;
	}

	@PostMapping(UrlConst.PRECOOK)
	public void serchPrecook() {

	}

}