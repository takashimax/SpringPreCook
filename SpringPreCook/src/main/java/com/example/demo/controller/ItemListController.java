package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.constant.db.ItemCategoryKind;
import com.example.demo.dto.ItemList;
import com.example.demo.form.ItemListForm;
import com.example.demo.service.ItemListService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemListController {
	
	private final ItemListService itemListService;

	@GetMapping(UrlConst.ITEM_LIST)
	public String view(Model model,ItemListForm itemListForm) {
	List<ItemList> itemCategories =  itemListService.editCategory();
	model.addAttribute("itemCategories", itemCategories); 
	model.addAttribute("ItemCategoryKinds", ItemCategoryKind.values());
		return ViewNameConst.ITEM_LIST;
	}

	@PostMapping(value =  UrlConst.ITEM_LIST, params = "search")
	public String searchCategory(Model model, ItemListForm itemListForm) {
		
		
		
		return null;
	}
}
