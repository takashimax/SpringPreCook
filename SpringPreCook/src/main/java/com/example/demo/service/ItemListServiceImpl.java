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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.constant.DeleteResult;
import com.example.demo.constant.UserEditMessage;
import com.example.demo.dto.DetailEditResult;
import com.example.demo.dto.ItemDetailList;
import com.example.demo.dto.ItemDetailSearchInfo;
import com.example.demo.dto.ItemDetailUpdateInfo;
import com.example.demo.dto.ItemEditResult;
import com.example.demo.dto.ItemList;
import com.example.demo.dto.ItemSeachInfo;
import com.example.demo.dto.ItemUpdateInfo;
import com.example.demo.entity.ItemCategory;
import com.example.demo.entity.ItemDetail;
import com.example.demo.form.ItemDetailListCreateForm;
import com.example.demo.form.ItemListCreateForm;
import com.example.demo.form.ItemListEditForm;
import com.example.demo.form.ItemListForm;
import com.example.demo.repository.ItemCategoryRepository;
import com.example.demo.repository.ItemDetailRepository;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemListServiceImpl implements ItemListService {

	/** プロフィール画像の保存先フォルダ */
	@Value("${image.folder}")
	private String imgFolder;

	/** プロフィール画像の保管拡張子 */
	@Value("${image.extract}")
	private String imgExtract;

	private final ItemCategoryRepository itemCategoryRepository;

	private final ItemDetailRepository itemDetailRepository;

	private final Mapper mapper;

	@Override
	public List<ItemList> editCategory() {
		return toItemCategories(itemCategoryRepository.findAll());
	}

	@Override
	public List<ItemDetailList> editDetail() {
		return toItemDetails(itemDetailRepository.findAll());
	}

	@Override
	public List<ItemList> editItemListByParam(ItemSeachInfo itemSeachInfo) {
		return toItemCategories(findItemInfoByParam(itemSeachInfo));
	}

	@Override
	public List<ItemList> editCategoryByPram(ItemSeachInfo itemSeachInfo) {
		return toItemCategories(findItemInfoByParam(itemSeachInfo));
	}

	@Override
	public List<ItemDetailList> editDetailByPram(ItemDetailSearchInfo itemDetailSearchInfo) {
		return toItemDetails(findDetailInfoByParam(itemDetailSearchInfo));
	}

	@Override
	public DeleteResult deleteCategoryByItemName(ItemListForm itemListForm) throws IOException {
		Optional<ItemCategory> itemCategoryOpt = itemCategoryRepository.findByItemName(itemListForm.getSelectedId());
		if (itemCategoryOpt.isEmpty()) {
			return DeleteResult.ITEM_ERROR;
		}
		List<ItemDetail> itemDetailOpt = itemDetailRepository.findByItemCategory(itemCategoryOpt.get());
		if (itemDetailOpt.isEmpty()) {
			itemCategoryRepository.deleteByItemName(itemCategoryOpt.get().getItemName());
			if (!itemCategoryOpt.get().getImageUrl().isEmpty()) {
				Path imageUrlPath = Path.of(imgFolder, itemCategoryOpt.get().getImageUrl());
				Files.delete(imageUrlPath);
			}
		} else {
			for (ItemDetail item : itemDetailOpt) {
				itemDetailRepository.deleteByItemCategory(item.getItemCategory());
				if (!item.getImageUrl().isEmpty()) {
					Path imageUrlPath = Path.of(imgFolder, item.getImageUrl());
					Files.delete(imageUrlPath);
				}
				Path imageUrlPath = Path.of(imgFolder, itemCategoryOpt.get().getImageUrl());
				Files.delete(imageUrlPath);
			}
		}

		return DeleteResult.ITEM_SUCCEED;
	}

	@Override
	public DeleteResult deleteDetailByItemCategory(ItemListEditForm itemListEditForm) {
		Optional<ItemDetail> itemDetailOpt = itemDetailRepository.findById(itemListEditForm.getSelectedDetailId());
		if (itemDetailOpt.isEmpty()) {
			return DeleteResult.ITEM_ERROR;
		}

		itemDetailRepository.deleteById(itemDetailOpt.get().getDetailId());
		return DeleteResult.ITEM_SUCCEED;
	}

	@Override
	public Optional<ItemCategory> searchItemCategory(String itemName) {
		return itemCategoryRepository.findByItemName(itemName);

	}

	@Override
	public Optional<ItemDetail> searchItemDetail(Integer detailId) {
		return itemDetailRepository.findById(detailId);

	}

	@Override
	public Optional<ItemCategory> createItemList(ItemListCreateForm itemListCreateForm) {
		Optional<ItemCategory> itemOptional = itemCategoryRepository.findByItemName(itemListCreateForm.getItemName());
		if (itemOptional.isPresent()) {
			return Optional.empty();
		}
		String saveImageUrl = null;
		if (!itemListCreateForm.getImageUrl().isEmpty()) {
			UUID uuid = UUID.randomUUID();
			saveImageUrl = uuid + imgExtract;
			Path imageUrlPath = Path.of(imgFolder, saveImageUrl);
			try {
				Files.copy(itemListCreateForm.getImageUrl().getInputStream(), imageUrlPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
			;
		}

		ItemCategory itemCategory = mapper.map(itemListCreateForm, ItemCategory.class);
		((ItemCategory) itemCategory).setId(null);
		((ItemCategory) itemCategory).setItemName(itemListCreateForm.getItemName());
		((ItemCategory) itemCategory).setItemCategoryKind(itemListCreateForm.getItemCategoryKind());
		((ItemCategory) itemCategory).setImageUrl(saveImageUrl);
		((ItemCategory) itemCategory).setCreateTime(LocalDateTime.now());
		((ItemCategory) itemCategory).setUpdateUser(SecurityContextHolder.getContext().getAuthentication().getName());

		return Optional.of(itemCategoryRepository.save(itemCategory));

	}

	@Override
	public Optional<ItemDetail> createItemDetailList(ItemDetailListCreateForm itemDetailListCreateForm) {

		String saveImageUrl = null;
		if (!itemDetailListCreateForm.getImageUrl().isEmpty()) {
			UUID uuid = UUID.randomUUID();
			saveImageUrl = uuid + imgExtract;
			Path imageUrlPath = Path.of(imgFolder, saveImageUrl);
			try {
				Files.copy(itemDetailListCreateForm.getImageUrl().getInputStream(), imageUrlPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
			;
		}

		ItemDetail itemDetail = mapper.map(itemDetailListCreateForm, ItemDetail.class);
		((ItemDetail) itemDetail).setDetailId(null);
		((ItemDetail) itemDetail).setItemCategory(itemDetailListCreateForm.getId());
		((ItemDetail) itemDetail).setImageUrl(saveImageUrl);
		((ItemDetail) itemDetail).setItineraryOrder(itemDetailListCreateForm.getItineraryOrder());
		((ItemDetail) itemDetail).setItineraryTitle(itemDetailListCreateForm.getItineraryTitle());
		((ItemDetail) itemDetail).setItemDetailText(itemDetailListCreateForm.getItemDetailText());

		return Optional.of(itemDetailRepository.save(itemDetail));

	}

	private List<ItemCategory> findItemInfoByParam(ItemSeachInfo itemSeachInfo) {
		String itemNameParam = AppUtil.addWildcard(itemSeachInfo.getItemName());

		if (itemSeachInfo.getItemCategoryKind() != null && itemSeachInfo.getItemName() != null) {
			return itemCategoryRepository.findByItemNameLikeAndItemCategoryKind(itemNameParam,
					itemSeachInfo.getItemCategoryKind());
		} else if (itemSeachInfo.getItemCategoryKind() != null) {
			return itemCategoryRepository.findByItemCategoryKind(itemSeachInfo.getItemCategoryKind());
		} else {
			return itemCategoryRepository.findByItemNameLike(itemNameParam);
		}
	}

	private List<ItemDetail> findDetailInfoByParam(ItemDetailSearchInfo itemDetailSearchInfo) {
		String itemTitleParam = AppUtil.addWildcard(itemDetailSearchInfo.getItineraryTitle());

		if (itemDetailSearchInfo.getItineraryTitle() != null && itemDetailSearchInfo.getItineraryOrder() != null) {
			return itemDetailRepository.findByItineraryTitleLikeAndItineraryOrder(itemTitleParam,
					itemDetailSearchInfo.getItineraryOrder());
		} else if (itemDetailSearchInfo.getItineraryTitle() != null) {
			return itemDetailRepository.findByItineraryTitleLike(itemTitleParam);
		} else {
			return itemDetailRepository
					.findByItineraryOrderOrderByItineraryOrder(itemDetailSearchInfo.getItineraryOrder());
		}
	}

	private List<ItemList> toItemCategories(List<ItemCategory> itemCategories) {
		var itemList = new ArrayList<ItemList>();
		for (ItemCategory itemCategory : itemCategories) {
			ItemList itemCategoryList = mapper.map(itemCategory, ItemList.class);
			((ItemList) itemCategoryList).setItemCategory(itemCategory.getItemCategoryKind().getDisplayValue());
			itemList.add((ItemList) itemCategoryList);
		}

		return itemList;

	}

	private List<ItemDetailList> toItemDetails(List<ItemDetail> itemDetails) {
		var itemList = new ArrayList<ItemDetailList>();
		for (ItemDetail itemDetail : itemDetails) {
			ItemDetailList itemDetailList = mapper.map(itemDetail, ItemDetailList.class);
			((ItemDetailList) itemDetailList).setItineraryTitle(itemDetail.getItineraryTitle());
			itemList.add((ItemDetailList) itemDetailList);
		}

		return itemList;

	}

	@Override
	public ItemEditResult updateCategoryInfo(ItemUpdateInfo itemUpdateInfo) {
		// 現在の登録情報を取得
		var itemUpdateResult = new ItemEditResult();

		Optional<ItemCategory> itemCategoryOpt = itemCategoryRepository.findByItemName(itemUpdateInfo.getItemName());
		if (itemCategoryOpt.isEmpty()) {
			itemUpdateResult.setUpdateMessage(UserEditMessage.FAILED);
			return itemUpdateResult;
		}

		// 画面の入力情報等をセット
		var itemCategory = itemCategoryOpt.get();
		itemCategory.setItemName(itemUpdateInfo.getItemName());
		itemCategory.setItemCategoryKind(itemUpdateInfo.getItemCategoryKind());
		itemCategory.setUpdateTime(LocalDateTime.now());
		String saveImageUrl = null;
		if (!itemUpdateInfo.getImageUrl().isEmpty()) {
			UUID uuid = UUID.randomUUID();
			saveImageUrl = uuid + imgExtract;
			Path imageUrlPath = Path.of(imgFolder, saveImageUrl);
			try {
				Files.copy(itemUpdateInfo.getImageUrl().getInputStream(), imageUrlPath);
				itemCategory.setImageUrl(saveImageUrl);
			} catch (IOException e) {
				e.printStackTrace();
			}
			;
		}

		try {
			itemCategoryRepository.save(itemCategory);
		} catch (Exception e) {
			itemUpdateResult.setUpdateMessage(UserEditMessage.FAILED);
			return itemUpdateResult;
		}

		itemUpdateResult.setUpdateItemCategory(itemCategory);
		itemUpdateResult.setUpdateMessage(UserEditMessage.SUCCEED);

		return itemUpdateResult;
	}

	@Override
	public DetailEditResult updateDetailInfo(ItemDetailUpdateInfo itemDetailUpdateInfo) {
		// 現在の登録情報を取得
		var detailUpdateResult = new DetailEditResult();

		Optional<ItemDetail> itemDetailOpt = itemDetailRepository.findById(itemDetailUpdateInfo.getDetailId());
		if (itemDetailOpt.isEmpty()) {
			detailUpdateResult.setUpdateMessage(UserEditMessage.FAILED);
			return detailUpdateResult;
		}

		// 画面の入力情報等をセット
		var itemDetail = itemDetailOpt.get();
		itemDetail.setItineraryOrder(itemDetailUpdateInfo.getItineraryOrder());
		itemDetail.setItineraryTitle(itemDetailUpdateInfo.getItineraryTitle());
		itemDetail.setItemDetailText(itemDetailUpdateInfo.getItemDetailText());
		;
		String saveImageUrl = null;
		if (!itemDetailUpdateInfo.getImageUrl().isEmpty()) {
			UUID uuid = UUID.randomUUID();
			saveImageUrl = uuid + imgExtract;
			Path imageUrlPath = Path.of(imgFolder, saveImageUrl);
			try {
				Files.copy(itemDetailUpdateInfo.getImageUrl().getInputStream(), imageUrlPath);
				itemDetail.setImageUrl(saveImageUrl);
			} catch (IOException e) {
				e.printStackTrace();
			}
			;
		}
		try {
			itemDetailRepository.save(itemDetail);
		} catch (Exception e) {
			detailUpdateResult.setUpdateMessage(UserEditMessage.FAILED);
			return detailUpdateResult;
		}

		detailUpdateResult.setUpdateItemDetail(itemDetail);
		detailUpdateResult.setUpdateMessage(UserEditMessage.SUCCEED);

		return detailUpdateResult;
	}

}
