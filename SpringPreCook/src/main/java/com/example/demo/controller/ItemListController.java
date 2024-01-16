package com.example.demo.controller;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.SessionKeyConst;
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.UserDeleteResult;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.constant.db.ItemCategoryKind;
import com.example.demo.dto.ItemList;
import com.example.demo.dto.ItemSeachInfo;
import com.example.demo.form.ItemListCreateForm;
import com.example.demo.form.ItemListForm;
import com.example.demo.service.ItemListService;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemListController {

	private final ItemListService itemListService;

	private final HttpSession session;

	private final MessageSource messageSource;
	
	private final Mapper mapper;

	@GetMapping(UrlConst.ITEM_LIST)
	public String view(Model model, ItemListForm itemListForm) {
		session.removeAttribute(SessionKeyConst.SELECETED_ID);

		List<ItemList> itemCategories = itemListService.editCategory();
		model.addAttribute("itemCategories", itemCategories);
		model.addAttribute("ItemCategoryKinds", ItemCategoryKind.values());
		return ViewNameConst.ITEM_LIST;
	}

	@GetMapping(UrlConst.ITEM_LIST_CREATE)
	public String viewCreate(Model model, ItemListCreateForm itemListCreateForm) {
		model.addAttribute("ItemCategoryKinds", ItemCategoryKind.values());
		return ViewNameConst.ITEM_LIST_CREATE;
	}

	@PostMapping(UrlConst.ITEM_LIST_CREATE)
	public String create(@Validated ItemListCreateForm itemListCreateForm, Model model, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ViewNameConst.ITEM_LIST_CREATE;
		} else {
			itemListService.createItemList(itemListCreateForm);
			return AppUtil.doRedirect(UrlConst.ITEM_LIST);
		}

	}

	@PostMapping(value = UrlConst.ITEM_LIST, params = "search")
	public String searchCategory(Model model, ItemListForm itemListForm) {
		ItemSeachInfo itemSeachInfo = mapper.map(itemListForm, ItemSeachInfo.class);
		List<ItemList> itemCategories = itemListService.editCategoryByPram(itemSeachInfo);
		model.addAttribute("itemCategories", itemCategories);
		model.addAttribute("ItemCategoryKinds", ItemCategoryKind.values());
		return ViewNameConst.ITEM_LIST;
	}

	@PostMapping(value = UrlConst.ITEM_LIST, params = "edit")
	public String updateCategory(ItemListForm itemListForm) {
		session.setAttribute(SessionKeyConst.SELECETED_ID, itemListForm.getSelectedId());
		return AppUtil.doRedirect(UrlConst.ITEM_LIST_EDIT);
	}

	@PostMapping(value = UrlConst.ITEM_LIST, params = "delete")
	public String deleteCategory(Model model, ItemListForm itemListForm) {
		var executeResult = itemListService.deleteCategoryByItemName(itemListForm.getSelectedId());
		model.addAttribute("isError", executeResult == UserDeleteResult.ERROR);
		model.addAttribute("message", AppUtil.getMessage(messageSource, executeResult.getMessageId()));

		// 削除後、フォーム情報の「選択されたログインID」は不要になるため、クリアします。
		return searchCategory(model, itemListForm.clearSelectedId());
	}
}
