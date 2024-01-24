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

/**
 * アイテムリスト一覧Serviceインターフェース
 * 
 */
public interface ItemListService {
	
	/**
	 * カテゴリー情報を検索しカテゴリーに必要な情報を返却
	 * @return
	 */
	public List<ItemList> editCategory();
	
	/**
	 * アイテム詳細情報を検索し詳細に必要な情報を返却
	 * @return
	 */
	public List<ItemDetailList> editDetail();
	
	/**
	 * カテゴリー情報を条件検索した結果を画面の表示用に変換して返却。
	 * @param itemSeachInfo
	 * @return
	 */
	public List<ItemList> editCategoryByPram(ItemSeachInfo itemSeachInfo);
	
	/**
	 * 指定されたカテゴリー情報を削除。
	 * @param itemListForm
	 * @return
	 * @throws IOException
	 */
	public DeleteResult deleteCategoryByItemName(ItemListForm itemListForm) throws IOException;
	
	/**
	 * 指定された詳細情報を削除
	 * @param itemListEditForm
	 * @return
	 */
	public DeleteResult deleteDetailByItemCategory(ItemListEditForm itemListEditForm);
	
	/**
	 * 新規アイテムリストの情報をFormから受け取りカテゴリーテーブルに登録。
	 * @param itemListCreateForm
	 * @return
	 */
	public Optional<ItemCategory> createItemList(ItemListCreateForm itemListCreateForm);
	
	/**
	 * カテゴリーテーブルから条件検索を行った結果を返却
	 * @param itemName
	 * @return
	 */
	public Optional<ItemCategory> searchItemCategory(String itemName);
	
	/**
	 * カテゴリーテーブルの更新。
	 * @param itemUpdateInfo
	 * @return
	 */
	public ItemEditResult updateCategoryInfo(ItemUpdateInfo itemUpdateInfo);
	
	/**
	 * 詳細テーブルの更新。
	 * @param itemDetailSearchInfo
	 * @return
	 */
	List<ItemDetailList> editDetailByPram(ItemDetailSearchInfo itemDetailSearchInfo);
	
	/**
	 * 詳細情報の検索結果を返却
	 * @param detailId
	 * @return
	 */
	Optional<ItemDetail> searchItemDetail(Integer detailId);
	
	/**
	 * 新規詳細情報をFormから受け取りカテゴリーテーブルに登録。
	 * @param itemDetailListCreateForm
	 * @return
	 */
	Optional<ItemDetail> createItemDetailList(ItemDetailListCreateForm itemDetailListCreateForm);
	
	/**
	 * カテゴリーテーブルの更新。
	 * @param itemSeachInfo
	 * @return
	 */
	List<ItemList> editItemListByParam(ItemSeachInfo itemSeachInfo);
	
	/**
	 * 
	 * @param itemDetailUpdateInfo
	 * @return
	 */
	DetailEditResult updateDetailInfo(ItemDetailUpdateInfo itemDetailUpdateInfo);


}
