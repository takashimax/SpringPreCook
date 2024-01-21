package com.example.demo.controller;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.SessionKeyConst;
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.UserEditMessage;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.dto.DetailEditResult;
import com.example.demo.dto.ItemDetailUpdateInfo;
import com.example.demo.dto.ItemList;
import com.example.demo.entity.ItemDetail;
import com.example.demo.form.ItemDetailListCreateForm;
import com.example.demo.form.ItemDetailListEditForm;
import com.example.demo.service.ItemListService;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemDetailListEditController {
	
	private final ItemListService itemListService;
	
	private final Mapper mapper;
	
	private final HttpSession session;
	
	private final MessageSource messageSource;

	@GetMapping(UrlConst.ITEM_DETAIL_LIST_EDIT)
	public String view(Model model, ItemDetailListEditForm itemDetailListEditForm) {
		Integer detailId = (Integer) session.getAttribute(SessionKeyConst.SELECETED_DETAIL_ID);
		Optional<ItemDetail> itemDetailOpt = itemListService.searchItemDetail(detailId);
		model.addAttribute("itemDetailOpt", itemDetailOpt.get());
		return ViewNameConst.ITEM_DETAIL_LIST_EDIT;
	}

	@GetMapping(UrlConst.ITEM_DETAIL_LIST_CREATE + "/{id}")
	public String viewCreate(@PathVariable(name = "id") Integer id,Model model, ItemDetailListCreateForm itemDetailListCreateForm) {
		model.addAttribute("id", id);
		return ViewNameConst.ITEM_DETAIL_LIST_CREATE;
	}

	@PostMapping(UrlConst.ITEM_DETAIL_LIST_CREATE)
	public String create(@Validated ItemDetailListCreateForm itemDetailListCreateForm, Model model, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ViewNameConst.ITEM_LIST_CREATE;
		} else {
			itemListService.createItemDetailList(itemDetailListCreateForm);
			return AppUtil.doRedirect(UrlConst.ITEM_LIST_EDIT);
		}

	}
	
	@PostMapping(value = UrlConst.ITEM_DETAIL_LIST_EDIT, params = "update")
	public String updateDetail(Model model, ItemDetailListEditForm itemDetailListEditForm, @AuthenticationPrincipal User user) {
		ItemDetailUpdateInfo updateDto = new ItemDetailUpdateInfo();
		updateDto.setDetailId((Integer) session.getAttribute(SessionKeyConst.SELECETED_DETAIL_ID));
		updateDto.setItineraryOrder(itemDetailListEditForm.getItineraryOrder());
		updateDto.setImageUrl(itemDetailListEditForm.getImageUrl());
		updateDto.setItineraryTitle(itemDetailListEditForm.getItineraryTitle());
		updateDto.setItemDetailText(itemDetailListEditForm.getItemDetailText());
		DetailEditResult updateResult = itemListService.updateDetailInfo(updateDto);
		UserEditMessage updateMessage = updateResult.getUpdateMessage();

		if (updateMessage == UserEditMessage.FAILED) {
			model.addAttribute("message", AppUtil.getMessage(messageSource, updateMessage.getMessageId()));
			return ViewNameConst.USER_EDIT_ERROR;
		}

		model.addAttribute("itemList", mapper.map(updateResult.getUpdateItemDetail(), ItemList.class));

		model.addAttribute("isError", false);
		model.addAttribute("message", AppUtil.getMessage(messageSource, updateMessage.getMessageId()));
		return AppUtil.doRedirect(UrlConst.ITEM_LIST_EDIT);
	}

}
