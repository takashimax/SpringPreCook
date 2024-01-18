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

import com.example.demo.constant.UserDeleteResult;
import com.example.demo.constant.UserEditMessage;
import com.example.demo.dto.ItemEditResult;
import com.example.demo.dto.ItemList;
import com.example.demo.dto.ItemSeachInfo;
import com.example.demo.dto.ItemUpdateInfo;
import com.example.demo.entity.ItemCategory;
import com.example.demo.entity.ItemDetail;
import com.example.demo.form.ItemListCreateForm;
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

	public List<ItemList> editItemListByParam(ItemSeachInfo itemSeachInfo) {
		return toItemCategories(findItemInfoByParam(itemSeachInfo));
	}

	@Override
	public List<ItemList> editCategoryByPram(ItemSeachInfo itemSeachInfo) {
		return toItemCategories(findItemInfoByParam(itemSeachInfo));
	}

	@Override
	public UserDeleteResult deleteCategoryByItemName(String itemName) {
		Optional<ItemCategory> itemCategoryOpt = itemCategoryRepository.findByItemName(itemName);
		if (itemCategoryOpt.isEmpty()) {
			return UserDeleteResult.ERROR;
		}
		itemCategoryRepository.deleteByItemName(itemName);
		return UserDeleteResult.SUCCEED;
	}

	@Override
	public UserDeleteResult deleteDetailByItemCategory(ItemCategory itemCategory) {
		Optional<ItemDetail> itemDetailOpt = itemDetailRepository.findByItemCategory(itemCategory);
		if (itemDetailOpt.isEmpty()) {
			return UserDeleteResult.ERROR;
		}
		itemDetailRepository.deleteByItemCategory(itemCategory);
		return UserDeleteResult.SUCCEED;
	}

	@Override
	public Optional<ItemCategory> searchItemCategory(String itemName) {
		return itemCategoryRepository.findByItemName(itemName);

	}

	@Override
	public Optional<ItemCategory> createItemList(ItemListCreateForm itemListCreateForm) {
		Optional<ItemCategory> itemOptional = itemCategoryRepository.findByItemName(itemListCreateForm.getItemName());
		if (itemOptional.isPresent()) {
			return Optional.empty();
		}
		UUID uuid = UUID.randomUUID();

		String saveImageUrl = uuid + imgExtract;
		Path imageUrlPath = Path.of(imgFolder, saveImageUrl);
		try {
			Files.copy(itemListCreateForm.getImageUrl().getInputStream(), imageUrlPath);
		} catch (IOException e) {
			e.printStackTrace();
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

	private List<ItemList> toItemCategories(List<ItemCategory> itemCategories) {
		var itemList = new ArrayList<ItemList>();
		for (ItemCategory itemCategory : itemCategories) {
			ItemList itemCategoryList = mapper.map(itemCategory, ItemList.class);
			((ItemList) itemCategoryList).setItemCategory(itemCategory.getItemCategoryKind().getDisplayValue());
			itemList.add((ItemList) itemCategoryList);
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

		UUID uuid = UUID.randomUUID();
		String saveImageUrl = uuid + imgExtract;
		Path imageUrlPath = Path.of(imgFolder, saveImageUrl);
		try {
			Files.copy(itemUpdateInfo.getImageUrl().getInputStream(), imageUrlPath);
			Files.delete(Path.of(itemCategoryOpt.get().getImageUrl()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 画面の入力情報等をセット
		var itemCategory = itemCategoryOpt.get();
		itemCategory.setItemName(itemUpdateInfo.getItemName());
		itemCategory.setItemCategoryKind(itemUpdateInfo.getItemCategoryKind());
		itemCategory.setUpdateTime(LocalDateTime.now());
		itemCategory.setImageUrl(saveImageUrl);
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

}
