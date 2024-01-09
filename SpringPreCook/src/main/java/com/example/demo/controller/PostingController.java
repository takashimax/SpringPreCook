package com.example.demo.controller;

import java.util.List;

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
import com.example.demo.service.PostingService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostingController {
	private final PostingService postingService;
	private final PostingRepository postingRepository;

	@GetMapping(UrlConst.POSTING)
	public String view(PostingForm postingForm, Model model) {
		List<PostingInfo> postingList = postingRepository.findAll();
		model.addAttribute("postingList", postingList);
		return "posting";
	}

	@PostMapping(UrlConst.POSTING)
	public String posting(@Validated PostingForm postingForm, BindingResult bindingResult, Model model) {
		model.addAttribute("posting", postingService.posting(postingForm));
		if(bindingResult.hasErrors()) {
			return ViewNameConst.POSTING;
		} else {
			postingService.posting(postingForm);
			return ViewNameConst.HOME;
		}
	}
}