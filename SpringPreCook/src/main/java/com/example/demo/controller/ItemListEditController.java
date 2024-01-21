package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.DeleteResult;
import com.example.demo.constant.SessionKeyConst;
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.UserEditMessage;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.constant.db.ItemCategoryKind;
import com.example.demo.dto.ItemEditResult;
import com.example.demo.dto.ItemList;
import com.example.demo.dto.ItemUpdateInfo;
import com.example.demo.entity.ItemCategory;
import com.example.demo.entity.ItemDetail;
import com.example.demo.form.ItemListEditForm;
import com.example.demo.repository.ItemDetailRepository;
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

	private final ItemDetailRepository itemDetailRepository;

	private final Mapper mapper;

	private final MessageSource messageSource;

	@GetMapping(UrlConst.ITEM_LIST_EDIT)
	public String view(Model model, ItemListEditForm itemListEditForm) {
		String itemName = (String) session.getAttribute(SessionKeyConst.SELECETED_ID);
		Optional<ItemCategory> itemCategoryOpt = itemListService.searchItemCategory(itemName);
		List<ItemDetail> itemDetailList = itemDetailRepository
				.findByItemCategoryOrderByItineraryOrder(itemCategoryOpt);
		if (itemCategoryOpt.isEmpty()) {
			return AppUtil.doRedirect(UrlConst.ITEM_LIST);
		} else {
			model.addAttribute("itemList", mapper.map(itemCategoryOpt.get(), ItemList.class));
			model.addAttribute("itemDetailList", itemDetailList);
			model.addAttribute("itemCategoryKinds", ItemCategoryKind.values());
			return ViewNameConst.ITEM_LIST_EDIT;
		}
	}

	@PostMapping(value = UrlConst.ITEM_LIST_EDIT, params = "update")
	public String updateCategory(Model model, ItemListEditForm itemListEditForm, @AuthenticationPrincipal User user) {
		ItemUpdateInfo updateDto = new ItemUpdateInfo();
		updateDto.setItemName(session.getAttribute(SessionKeyConst.SELECETED_ID).toString());
		updateDto.setItemCategoryKind(itemListEditForm.getItemCategoryKind());
		updateDto.setImageUrl(itemListEditForm.getImageUrl());
		updateDto.setUpdateUserId(user.getUsername());
		ItemEditResult updateResult = itemListService.updateCategoryInfo(updateDto);
		UserEditMessage updateMessage = updateResult.getUpdateMessage();

		if (updateMessage == UserEditMessage.FAILED) {
			model.addAttribute("message", AppUtil.getMessage(messageSource, updateMessage.getMessageId()));
			return ViewNameConst.USER_EDIT_ERROR;
		}

		model.addAttribute("itemList", mapper.map(updateResult.getUpdateItemCategory(), ItemList.class));

		model.addAttribute("isError", false);
		model.addAttribute("message", AppUtil.getMessage(messageSource, updateMessage.getMessageId()));
		return AppUtil.doRedirect(UrlConst.ITEM_LIST);
	}

	@PostMapping(value = UrlConst.ITEM_LIST_EDIT, params = "edit")
	public String editDetail(Model model, ItemListEditForm itemListEditForm) {
		session.setAttribute(SessionKeyConst.SELECETED_DETAIL_ID, itemListEditForm.getSelectedDetailId());
		return AppUtil.doRedirect(UrlConst.ITEM_DETAIL_LIST_EDIT);
	}

//	@PostMapping(value = UrlConst.ITEM_LIST_EDIT, params = "search")
//	public String searchDetail(Model model, ItemListEditForm itemListEditForm) {
//		System.out.println("search");
////		ItemDetailSearchInfo itemSeachInfo = mapper.map(itemListEditForm, ItemDetailSearchInfo.class);
////		List<ItemDetailList> itemDetailList = itemListService.editDetailByPram(itemSeachInfo);
////		model.addAttribute("itemDetailList", itemDetailList);
////		model.addAttribute("ItemCategoryKinds", ItemCategoryKind.values());
//		return AppUtil.doRedirect(UrlConst.ITEM_LIST);
//	}

	@PostMapping(value = UrlConst.ITEM_LIST_EDIT, params = "delete")
	@Transactional
	public String deleteDetail(Model model, ItemListEditForm itemListEditForm) {
		System.out.println("delete");
		DeleteResult executeResult = itemListService.deleteDetailByItemCategory(itemListEditForm);
		System.out.println(executeResult);
		model.addAttribute("isError", executeResult == DeleteResult.ITEM_ERROR);
		model.addAttribute("message", AppUtil.getMessage(messageSource, executeResult.getMessageId()));
		// 削除後、フォーム情報の「選択されたログインID」は不要になるため、クリアします。
		itemListEditForm.clearSelectedDetalId();
		
		return AppUtil.doRedirect(UrlConst.ITEM_LIST_EDIT);
	}

}
