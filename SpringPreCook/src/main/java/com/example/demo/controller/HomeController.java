package com.example.demo.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.constant.db.ItemCategoryKind;
import com.example.demo.entity.ItemCategory;
import com.example.demo.repository.ItemCategoryRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	private final ItemCategoryRepository itemCategoryRepository;

	@GetMapping(UrlConst.HOME)
	public String view(@AuthenticationPrincipal User user, Model model) {
		List<ItemCategory> itemCategories = itemCategoryRepository.findAll();
		model.addAttribute("itemCategories", itemCategories);
		model.addAttribute("ItemCategoryKinds", ItemCategoryKind.values());
		System.out.println(itemCategories);
		
		return ViewNameConst.HOME;
	}
	
	@PostMapping(UrlConst.HOME)
	public String posting( RedirectAttributes redirectAttributes) {
		return ViewNameConst.POSTING;
	}

}