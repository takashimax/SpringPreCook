package com.example.demo.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.entity.PostingDetail;
import com.example.demo.entity.PostingInfo;
import com.example.demo.entity.PostingMaterial;
import com.example.demo.service.PostingService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostingEditController {

	private final PostingService postingService;


	@GetMapping(UrlConst.POSTING_EDIT + "/{id}")
	public String view(@PathVariable(name = "id") Integer id, Model model) {
		Optional<PostingInfo> postingInfoOpt = postingService.findPostingInfos(id);

		if (postingInfoOpt.isEmpty()) {
			AppUtil.doRedirect(UrlConst.POSTING_EDIT);
		} else {
			Optional<PostingMaterial> postingMaterilOpt = postingService.findPostingMaterial(postingInfoOpt.get());
			System.out.println(postingMaterilOpt);
			Optional<PostingDetail> postingDetailOpt = postingService.findPostingDetail(postingInfoOpt.get());

			model.addAttribute("postingInfoOpt", postingInfoOpt.get());
			model.addAttribute("postingMaterilOpt", postingMaterilOpt.get());
			model.addAttribute("postingDetailOpt", postingDetailOpt.get());
		}
		return ViewNameConst.POSTING_EDIT;
	}

	@PostMapping(UrlConst.POSTING_EDIT)
	public void postingEdit() {

	}
}
