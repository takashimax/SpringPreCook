package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
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

/**
 * 
 * 投稿内容コントローラークラス
 */
@Controller
@RequiredArgsConstructor
public class PostingEditController {

	private final PostingService postingService;

	@Value("${image.notexist}")
	private String imgNotExist;
	
	/**
	 * 選択された投稿内容の詳細を表示
	 * @param id
	 * @param user
	 * @param model
	 * @return
	 */
	@GetMapping(UrlConst.POSTING_EDIT + "/{id}")
	public String view(@PathVariable Integer id, @AuthenticationPrincipal User user, Model model) {
		Optional<PostingInfo> postingInfoOpt = postingService.findPostingInfos(id);

		if (postingInfoOpt.isEmpty()) {
			AppUtil.doRedirect(UrlConst.POSTING_EDIT);
		} else {
			List<PostingMaterial> postingMaterilOpt = postingService.findPostingMaterial(postingInfoOpt.get());
			List<PostingDetail> postingDetailOpt = postingService.findPostingDetail(postingInfoOpt.get());

			model.addAttribute("postingInfoOpt", postingInfoOpt.get());
			model.addAttribute("postingMaterilOpt", postingMaterilOpt);
			model.addAttribute("postingDetailOpt", postingDetailOpt);
			model.addAttribute("imgNotExist", imgNotExist);
		}
		return ViewNameConst.POSTING_EDIT;
	}

	@PostMapping(UrlConst.POSTING_EDIT)
	public void postingEdit() {

	}
}
