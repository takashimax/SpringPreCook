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
import com.example.demo.entity.ItemCategoryInfo;
import com.example.demo.form.ItemCategoryForm;
import com.example.demo.repository.ItemCategoryRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	private final ItemCategoryRepository itemCategoryRepository;

	@GetMapping(UrlConst.HOME)
	public String view(@AuthenticationPrincipal User user, Model model) {
		List<ItemCategoryInfo> vegetables = itemCategoryRepository.findByItemGenre(1);
		List<ItemCategoryInfo> meats = itemCategoryRepository.findByItemGenre(2);
		List<ItemCategoryInfo> fishes = itemCategoryRepository.findByItemGenre(3);
		model.addAttribute("vegetables", vegetables);
		model.addAttribute("meats", meats);
		model.addAttribute("fishes", fishes);
		List<ItemCategoryInfo> itemCategoryInfos = itemCategoryRepository.findAll();
		model.addAttribute("itemCategoryInfos", itemCategoryInfos);
		return ViewNameConst.HOME;
	}
//
	@PostMapping("/")
	public String posting(ItemCategoryForm itemCategoryform, RedirectAttributes redirectAttributes) {
		String itemCategoryInfo = itemCategoryform.getItemName();
		redirectAttributes.addFlashAttribute("itemCategoryInfo",
				itemCategoryRepository.findByItemName(itemCategoryInfo));
		return "precook";
	}

}