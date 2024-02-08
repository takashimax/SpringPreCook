package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.DeleteResult;
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.entity.PostingInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.PostingForm;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.service.PostingService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

/**
 * 
 * @author 7d14
 * 投稿画面コントローラークラス
 */
@Controller
@RequiredArgsConstructor
public class PostingController {

	private final PostingService postingService;

	private final UserInfoRepository userInfoRepository;

	private final MessageSource messageSource;

	@Value("${image.notexist}")
	private String imgNotExist;

	@GetMapping(UrlConst.POSTING)
	public String view(PostingForm postingForm, Model model, @AuthenticationPrincipal User user) {
		String loginId = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<UserInfo> userInfo = userInfoRepository.findByLoginId(loginId);
		List<PostingInfo> postingList = postingService.findPost(userInfo.get());
		model.addAttribute("postingList", postingList);
		model.addAttribute("imgNotExist", imgNotExist);
		return ViewNameConst.POSTING;
	}

	@PostMapping(UrlConst.POSTING)
	public String postingCon(@Validated PostingForm postingForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return ViewNameConst.POSTING;
		} else {
			String loginId = SecurityContextHolder.getContext().getAuthentication().getName();
			Optional<UserInfo> userInfo = userInfoRepository.findByLoginId(loginId);
			postingService.createPostingResult(postingForm, userInfo.get());
			return AppUtil.doRedirect(UrlConst.HOME);
		}
	}

	/**
	 * 
	 * 投稿ページから入力された情報を受け取りサービスクラスへ受け渡しリダイレクトします。
	 * @param id
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@PostMapping(value = UrlConst.POSTING + "/{id}")
	@Transactional
	public String postingDelete(@PathVariable Integer id, Model model) throws IOException {
		DeleteResult executeResult = postingService.deletePosting(id);
		model.addAttribute("isError", executeResult == DeleteResult.ITEM_ERROR);
		model.addAttribute("message", AppUtil.getMessage(messageSource, executeResult.getMessageId()));

		return AppUtil.doRedirect(UrlConst.POSTING);
	}
}