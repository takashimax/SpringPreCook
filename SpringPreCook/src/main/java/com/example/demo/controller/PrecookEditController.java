package com.example.demo.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.entity.ItemCategory;
import com.example.demo.entity.ItemDetail;
import com.example.demo.form.PrecookEditForm;
import com.example.demo.repository.ItemCategoryRepository;
import com.example.demo.repository.ItemDetailRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PrecookEditController {
	
	private final ItemCategoryRepository itemCategoryRepository;
	private final ItemDetailRepository itemDetailRepository;

	@GetMapping(UrlConst.PRECOOKEDIT)
	public String view(Model model) {
	List<ItemCategory> itemCategories =  itemCategoryRepository.findAll();
	List<ItemDetail> itemDetails = itemDetailRepository.findAll();
	model.addAttribute("itemCategoryInfos", itemCategories); 
	model.addAttribute("itemDetailInfos", itemDetails);
		return ViewNameConst.PRECOOKEDIT;
	}

	@PostMapping(value =  UrlConst.PRECOOKEDIT, params = "search")
	public String precookEdit(@AuthenticationPrincipal User user,
			@Validated PrecookEditForm precookEditForm,
			BindingResult bindingResult, Model model) {
		List<ItemCategory> itemCategories =  itemCategoryRepository.findAll();
		List<ItemDetail> itemDetails = itemDetailRepository.findAll();
		model.addAttribute("itemCategoryInfos", itemCategories); 
		model.addAttribute("itemDetailInfos", itemDetails);
		return null;
	}
}
