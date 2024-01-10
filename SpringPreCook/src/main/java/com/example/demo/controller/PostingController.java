package com.example.demo.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.entity.PostingInfo;
import com.example.demo.form.PostingForm;
import com.example.demo.repository.PostingRepository;
import com.example.demo.service.PostingServiceImpl;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostingController {
	private final PostingServiceImpl postingServiceImpl;
	private final PostingRepository postingRepository;

	@GetMapping(UrlConst.POSTING)
	public String view(PostingForm postingForm, Model model) {
		String loginId = SecurityContextHolder.getContext().getAuthentication().getName();
		List<PostingInfo> postingList = postingRepository.findByLoginId(loginId);
		model.addAttribute("postingList", postingList);
		return ViewNameConst.POSTING;
	}

	@PostMapping(UrlConst.POSTING)
	public String posting(@Validated PostingForm postingForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return ViewNameConst.POSTING;
		} else {
			postingServiceImpl.posting(postingForm);
			return AppUtil.doRedirect(UrlConst.HOME);
		}
	}
}