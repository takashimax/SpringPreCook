package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.example.demo.constant.DeleteResult;
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

public interface ItemListService {

	public List<ItemList> editCategory();

	public List<ItemDetailList> editDetail();

	public List<ItemList> editCategoryByPram(ItemSeachInfo itemSeachInfo);

	public DeleteResult deleteCategoryByItemName(ItemListForm itemListForm) throws IOException;

	public DeleteResult deleteDetailByItemCategory(ItemListEditForm itemListEditForm);

	public Optional<ItemCategory> createItemList(ItemListCreateForm itemListCreateForm);

	public Optional<ItemCategory> searchItemCategory(String itemName);

	public ItemEditResult updateCategoryInfo(ItemUpdateInfo itemUpdateInfo);

	List<ItemDetailList> editDetailByPram(ItemDetailSearchInfo itemDetailSearchInfo);

	Optional<ItemDetail> searchItemDetail(Integer detailId);

	Optional<ItemDetail> createItemDetailList(ItemDetailListCreateForm itemDetailListCreateForm);

	List<ItemList> editItemListByParam(ItemSeachInfo itemSeachInfo);

	DetailEditResult updateDetailInfo(ItemDetailUpdateInfo itemDetailUpdateInfo);


}
