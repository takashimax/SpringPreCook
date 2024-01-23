package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.constant.DeleteResult;
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
	public List<PostingDetail> findPostingDetail(PostingInfo postingInfo) {
		return postingDetailRepository.findByPostingInfo(postingInfo);
	}

	@Override
	public List<PostingMaterial> findPostingMaterial(PostingInfo postingInfo) {
		return postingMaterialRepository.findByPostingInfo(postingInfo);
	}

	@Override
	public List<PostingMaterial> findPostingMaterialLike(String postingName) {
		return postingMaterialRepository.findByMaterialNameLike(postingName);
	}

	@Override
	public Optional<PostingInfo> createPostInfo(PostingForm postingForm, UserInfo userInfo) {
		String saveImageUrl = "";
		if (!postingForm.getImageUrl().isEmpty()) {
			UUID uuid = UUID.randomUUID();
			saveImageUrl = uuid + imgExtract;
			Path imageUrlPath = Path.of(imgFolder, saveImageUrl);
			try {
				Files.copy(postingForm.getImageUrl().getInputStream(), imageUrlPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
			;
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

		List<PostingMaterial> postingMaterialList = new ArrayList<PostingMaterial>();
		for (int n = 0; n <= postingForm.getMaterialList().size() - 1; n++) {
			PostingMaterial postingMaterial = mapper.map(postingForm, PostingMaterial.class);
			((PostingMaterial) postingMaterial).setPostingInfo(postingInfo);
			((PostingMaterial) postingMaterial).setMaterialName(postingForm.getMaterialList().get(n).getMaterialName());
			((PostingMaterial) postingMaterial)
					.setMaterialQuantity(postingForm.getMaterialList().get(n).getMaterialQuantity());
			((PostingMaterial) postingMaterial).setMaterialOrder(n + 1);
			postingMaterialList.add(postingMaterial);
		}
		postingMaterialRepository.saveAll(postingMaterialList);

	}

	@Override
	public void createPostDetail(PostingForm postingForm, PostingInfo postingInfo) {

		List<PostingDetail> postingDetailList = new ArrayList<PostingDetail>();

		for (int n = 0; n <= postingForm.getDetailLists().size() - 1; n++) {
			PostingDetail postingDetail = mapper.map(postingForm, PostingDetail.class);
			((PostingDetail) postingDetail).setPostingInfo(postingInfo);
			((PostingDetail) postingDetail).setDetailOrder(n + 1);
			String saveImageUrl = "";
			if (!postingForm.getDetailLists().get(n).getDetailImageUrl().isEmpty()) {
				UUID uuid = UUID.randomUUID();
				saveImageUrl = uuid + imgExtract;
				Path imageUrlPath = Path.of(imgFolder, saveImageUrl);
				try {
					Files.copy(postingForm.getDetailLists().get(n).getDetailImageUrl().getInputStream(), imageUrlPath);
				} catch (IOException e) {
					e.printStackTrace();
				}
				;
			}
			((PostingDetail) postingDetail)
					.setImageUrl(saveImageUrl);
			((PostingDetail) postingDetail)
					.setPostingDetailText(postingForm.getDetailLists().get(n).getPostingDetailText());
			postingDetailList.add(postingDetail);
		}
		System.out.println(postingDetailList);
		postingDetailRepository.saveAll(postingDetailList);

	}

	@Override
	@Transactional
	public void createPostingResult(PostingForm postingForm, UserInfo userInfo) {
		Optional<PostingInfo> postingInfo = createPostInfo(postingForm, userInfo);
		createPostMaterial(postingForm, postingInfo.get());
		createPostDetail(postingForm, postingInfo.get());
	}

	@Override
	@Transactional
	public DeleteResult deletePosting(Integer id) throws IOException {
		Optional<PostingInfo> postingOpt = postingInfoRepository.findById(id);
		if (postingOpt.isEmpty()) {
			return DeleteResult.ITEM_ERROR;
		}

		List<PostingMaterial> postingMaterials = postingMaterialRepository.findByPostingInfo(postingOpt.get());
		List<PostingDetail> postingDetails = postingDetailRepository.findByPostingInfo(postingOpt.get());
		if (postingMaterials.isEmpty() && postingDetails.isEmpty()) {
			postingInfoRepository.deleteById(id);
		} else {
			for (PostingMaterial material : postingMaterials) {
				postingMaterialRepository.deleteByPostingInfo(material.getPostingInfo());
			}
			for (PostingDetail detail : postingDetails) {
				postingDetailRepository.deleteByPostingInfo(detail.getPostingInfo());
				if (!detail.getImageUrl().isEmpty()) {
					Path imageUrlPath = Path.of(imgFolder, detail.getImageUrl());
					Files.delete(imageUrlPath);
				}
			}

			postingInfoRepository.deleteById(id);
			if (!postingOpt.get().getImageUrl().isEmpty()) {
				Path imageUrlPath = Path.of(imgFolder, postingOpt.get().getImageUrl());
				Files.delete(imageUrlPath);
			}
		}

		return DeleteResult.ITEM_SUCCEED;
	}

}