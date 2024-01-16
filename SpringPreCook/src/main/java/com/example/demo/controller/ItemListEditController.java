package com.example.demo.controller;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.SessionKeyConst;
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.UserEditMessage;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.constant.db.ItemCategoryKind;
import com.example.demo.dto.ItemEditResult;
import com.example.demo.dto.ItemList;
import com.example.demo.dto.ItemUpdateInfo;
import com.example.demo.entity.ItemCategory;
import com.example.demo.form.ItemListEditForm;
import com.example.demo.service.ItemListService;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemListEditController {
	
	private final HttpSession session;
	
	private final ItemListService itemListService;
	 
	private final Mapper mapper;
	
	private final MessageSource messageSource;
	
	@GetMapping(UrlConst.ITEM_LIST_EDIT)
	public String view(Model model, ItemListEditForm itemListEditForm) {
		String itemName = (String) session.getAttribute(SessionKeyConst.SELECETED_ID);
		Optional<ItemCategory> itemCategoryOpt = itemListService.searchItemCategory(itemName);
		if(itemCategoryOpt.isEmpty()) {
			return AppUtil.doRedirect(UrlConst.ITEM_LIST);
		}else {
			model.addAttribute("itemList", mapper.map(itemCategoryOpt.get(), ItemList.class));
			model.addAttribute("itemCategoryKinds", ItemCategoryKind.values());
			System.out.println("ok");
			return ViewNameConst.ITEM_LIST_EDIT;
		}
	}
	
	@PostMapping(value = UrlConst.ITEM_LIST_EDIT, params = "update")
	public String updateCategory(Model model, ItemListEditForm itemListEditForm) {
		ItemUpdateInfo updateDto = mapper.map(itemListEditForm, ItemUpdateInfo.class);
		updateDto.setItemName(itemListEditForm.getItemName());
		updateDto.setItemCategoryKind(itemListEditForm.getItemCategoryKind());
		updateDto.setImageUrl(itemListEditForm.getImageUrl());
		ItemEditResult updateResult = itemListService.updateCategoryInfo(updateDto);
		UserEditMessage updateMessage = updateResult.getUpdateMessage();
		
		if (updateMessage == UserEditMessage.FAILED) {
			model.addAttribute("message", AppUtil.getMessage(messageSource, updateMessage.getMessageId()));
			return ViewNameConst.USER_EDIT_ERROR;
		}
		
		model.addAttribute("itemList", mapper.map(updateResult.getUpdateItemCategory(),ItemList.class));

		model.addAttribute("isError", false);
		model.addAttribute("message", AppUtil.getMessage(messageSource, updateMessage.getMessageId()));
		return ViewNameConst.USER_EDIT;
	}
}
