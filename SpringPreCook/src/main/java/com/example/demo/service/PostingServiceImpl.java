package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.PostingInfo;
import com.example.demo.form.PostingForm;
import com.example.demo.repository.PostingInfoRepository;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PostingServiceImpl implements PostingService {


	/** プロフィール画像の保存先フォルダ */
	@Value("${image.folder}")
	private String imgFolder;

	/** プロフィール画像の保管拡張子 */
	@Value("${image.extract}")
	private String imgExtract;

	private final PostingInfoRepository postingInfoRepository;
	private final Mapper mapper;
	
	public List<PostingInfo> findAll() {
		return postingInfoRepository.findAll();
	}
	
	@Override
	public void posting(PostingForm postingForm, Integer itemId) {
		UUID uuid = UUID.randomUUID();
		
		String saveImageUrl = uuid + imgExtract;
		Path imageUrlPath = Path.of(imgFolder, saveImageUrl);
		try {
			Files.copy(postingForm.getImageUrl().getInputStream(), imageUrlPath);

			PostingInfo postingInfo = mapper.map(postingForm, PostingInfo.class);
			postingInfo.setUserInfo(SecurityContextHolder.getContext().getAuthentication().getName());
			postingInfo.setImageUrl(saveImageUrl);
			postingInfo.setPostingTitle(postingForm.getPostingTitle());
			postingInfo.setPostingText(postingForm.getPostingText());
			postingInfo.setCreateTime(LocalDateTime.now());
			postingInfo.setUpdateTime(LocalDateTime.now());
			postingInfoRepository.save(postingInfo);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
	}
	
}