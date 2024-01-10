package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.PostingInfo;
import com.example.demo.form.PostingForm;
import com.example.demo.repository.PostingRepository;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PostingService {

	private final PostingRepository postingRepository;
	private final Mapper mapper;

	public List<PostingInfo> findAll() {
		return postingRepository.findAll();
	}

	public Optional<PostingInfo> posting(PostingForm postingForm) {
		PostingInfo postingInfo = mapper.map(postingForm, PostingInfo.class);
		postingInfo.setLoginId(SecurityContextHolder.getContext().getAuthentication().getName());
		postingInfo.setImageUrl(postingForm.getImageUrl());
		postingInfo.setPostingTitle(postingForm.getPostingTitle());
		postingInfo.setPostingText(postingForm.getPostingText());
		postingInfo.setCreateTime(LocalDateTime.now());
		postingInfo.setUpdateTime(LocalDateTime.now());
		return Optional.of(postingRepository.save(postingInfo));
	}
}