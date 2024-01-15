package com.example.demo.constant;

/**
 * URL定数クラス
 * 
 * @author ys-fj
 *
 */
public class UrlConst {

	/** ログイン画面 */
	public static final String LOGIN = "/login";

	/** ユーザー登録画面 */
	public static final String SIGNUP = "/signup";

	/** メニュー画面 */
	public static final String MENU = "/menu";

	/** ユーザー一覧画面 */
	public static final String USER_LIST = "/userList";

	/** ユーザー編集画面 */
	public static final String USER_EDIT = "/userEdit";

	public static final String HOME = "/";

	public static final String POSTING = "/posting";

	public static final String PRECOOK = "/precook";
	
	public static final String PRECOOKEDIT = "/precookEdit";

	/** 認証不要画面 */
	public static final String[] NO_AUTHENTICATION = { LOGIN, SIGNUP, HOME, PRECOOK, "/precook/**", "/webjars/**",
			"/css/**", "/img/**", "/js/**" };

	/** 認証必要画面 */
	public static final String[] ADMIN_AUTHENTICATION = { USER_LIST, USER_EDIT, MENU ,PRECOOKEDIT};
}