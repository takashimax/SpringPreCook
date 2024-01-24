package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 処理結果種別
 * 
 * @author 7d14
 */
@Getter
@AllArgsConstructor
public enum DeleteResult {

	/* エラーなし */
	USER_SUCCEED(MessageConst.USERLIST_DELETE_SUCCEED),

	/* エラーあり */
	USER_ERROR(MessageConst.USERLIST_NON_EXISTED_LOGIN_ID),
	
	/* エラーなし */
	ITEM_SUCCEED(MessageConst.ITEMLIST_DELETE_SUCCEED),
	
	/* エラーあり */
	ITEM_ERROR(MessageConst.ITEMLIST_NON_EXISTED_ITEM_NAME),;

	/** メッセージID */
	private String messageId;

}