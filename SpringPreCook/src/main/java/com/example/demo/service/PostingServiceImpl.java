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

import com.example.demo.entity.PostingDetail;
import com.example.demo.entity.PostingInfo;
import com.example.demo.entity.PostingMaterial;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.PostingForm;
import com.example.demo.repository.PostingDetailRepository;
import com.example.demo.repository.PostingInfoRepository;
import com.example.demo.repository.PostingMaterialRepository;
import com.github.dozermapper.core.Mapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PostingServiceImpl implements PostingService {

	@Value("${image.folder}")
	private String imgFolder;

	@Value("${image.extract}")
	private String imgExtract;

	private final PostingInfoRepository postingInfoRepository;

	private final PostingMaterialRepository postingMaterialRepository;

	private final PostingDetailRepository postingDetailRepository;

	private final Mapper mapper;

	public List<PostingInfo> findPost(UserInfo userInfo) {
		return postingInfoRepository.findByUserInfo(userInfo);
	}

	@Override
	public Optional<PostingInfo> findPostingInfos(Integer id) {
		return postingInfoRepository.findById(id);
	}

	@Override
	public Optional<PostingDetail> findPostingDetail(PostingInfo postingInfo) {
		return postingDetailRepository.findByPostingInfo(postingInfo);
	}

	@Override
	public Optional<PostingMaterial> findPostingMaterial(PostingInfo postingInfo) {
		return postingMaterialRepository.findByPostingInfo(postingInfo);
	}

	@Override
	public Optional<PostingInfo> createPostInfo(PostingForm postingForm, UserInfo userInfo) {
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
		((PostingInfo) postingInfo).setUserInfo(userInfo);
		((PostingInfo) postingInfo).setPostingTitle(postingForm.getPostingTitle());
		((PostingInfo) postingInfo).setPostingText(postingForm.getPostingText());
		((PostingInfo) postingInfo).setImageUrl(saveImageUrl);
		((PostingInfo) postingInfo).setCreateTime(LocalDateTime.now());

		return Optional.of(postingInfoRepository.save(postingInfo));

	}

	@PersistenceContext
	private EntityManager em;

	@Override
	public void createPostMaterial(PostingForm postingForm, PostingInfo postingInfo) {

		PostingMaterial postingMaterial = mapper.map(postingForm, PostingMaterial.class);
		for (int n = 0; n <= postingForm.getMaterialList().size() - 1; n++) {
			postingMaterial.setPostingInfo(postingInfo);
			postingMaterial.setMaterialName(postingForm.getMaterialList().get(n).getMaterialName());
			postingMaterial.setMaterialOrder(n + 1);
			this.em.persist(postingMaterial);
			System.out.println(postingMaterial);
		}

	}

	@Override
	public Optional<PostingDetail> createPostDetail(PostingForm postingForm, PostingInfo postingInfo) {

		UUID uuid = UUID.randomUUID();
		String saveImageUrl = uuid + imgExtract;
		Path imageUrlPath = Path.of(imgFolder, saveImageUrl);
		try {
			Files.copy(postingForm.getDetailImageUrl().getInputStream(), imageUrlPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		PostingDetail postingDetail = mapper.map(postingForm, PostingDetail.class);
		postingDetail.setId(null);
		postingDetail.setPostingInfo(postingInfo);
		postingDetail.setDetailOrder(postingForm.getDetailOrder());
		postingDetail.setImageUrl(saveImageUrl);
		postingDetail.setPostingDetailText(postingForm.getPostingDetailText());

		return Optional.of(postingDetailRepository.save(postingDetail));
	}

	@Override
	@Transactional
	public void createPostingResult(PostingForm postingForm, UserInfo userInfo) {
		Optional<PostingInfo> postingInfo = createPostInfo(postingForm, userInfo);
		createPostMaterial(postingForm, postingInfo.get());
		createPostDetail(postingForm, postingInfo.get());
	}

}