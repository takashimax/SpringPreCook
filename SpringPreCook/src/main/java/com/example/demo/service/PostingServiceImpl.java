package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.PostingInfo;
import com.example.demo.entity.UserInfo;
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

	public List<PostingInfo> findPost(UserInfo userInfo) {
		return postingInfoRepository.findByUserInfo(userInfo);
	}

	@Override
	public Optional<PostingInfo> createPost(PostingForm postingForm) {
		UUID uuid = UUID.randomUUID();

		String saveImageUrl = uuid + imgExtract;
		Path imageUrlPath = Path.of(imgFolder, saveImageUrl);
		try {
			Files.copy(postingForm.getImageUrl().getInputStream(), imageUrlPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		PostingInfo postingInfo = mapper.map(postingForm, PostingInfo.class);
		((PostingInfo) postingInfo).setId(null);
		((PostingInfo) postingInfo).setUserInfo(null);
		((PostingInfo) postingInfo).setPostingTitle(postingForm.getPostingTitle());
		((PostingInfo) postingInfo).setPostingText(postingForm.getPostingText());
		((PostingInfo) postingInfo).setImageUrl(saveImageUrl);
		((PostingInfo) postingInfo).setCreateTime(LocalDateTime.now());

		return Optional.of(postingInfoRepository.save(postingInfo));

	}

}