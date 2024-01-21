package com.example.demo.constant;

/**
 * URL定数クラス
 * 
 * @author ys-fj
 *
 */
public class UrlConst {

	public static final String LOGIN = "/login";

	public static final String SIGNUP = "/signup";

	public static final String MENU = "/menu";

	public static final String USER_LIST = "/userList";

	public static final String USER_EDIT = "/userEdit";

	public static final String HOME = "/";

	public static final String POSTING = "/posting";

	public static final String POSTING_EDIT = "/postingEdit";

	public static final String PRECOOK = "/precook";

	public static final String ITEM_LIST = "/itemList";

	public static final String ITEM_EDIT = "/itemEdit";

	public static final String ITEM_LIST_CREATE = "/itemListCreate";

	public static final String ITEM_LIST_EDIT = "/itemListEdit";

	public static final String ITEM_DETAIL_LIST_EDIT = "/itemDetailListEdit";

	public static final String ITEM_DETAIL_LIST_CREATE = "/itemDetailListCreate";

	/** 認証不要画面 */
	public static final String[] NO_AUTHENTICATION = { LOGIN, SIGNUP, HOME, PRECOOK + "/**", "/webjars/**",
			"/css/**", "/img/**", "/js/**" };

	/** 認証必要画面 */
	public static final String[] ADMIN_AUTHENTICATION = { USER_LIST, USER_EDIT, MENU, ITEM_LIST, ITEM_LIST_CREATE,
			ITEM_LIST_EDIT, POSTING_EDIT + "/**", ITEM_DETAIL_LIST_EDIT, ITEM_DETAIL_LIST_CREATE + "/**" };
}