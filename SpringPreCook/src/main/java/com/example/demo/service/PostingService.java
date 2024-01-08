package com.example.demo.service;

import java.util.List;
import java.util.Optional;

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
		return Optional.of(postingRepository.save(postingInfo));
	}
}