package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.constant.DeleteResult;
import com.example.demo.dto.UserListInfo;
import com.example.demo.dto.UserSearchInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー一覧画面Service実装クラス
 * 
 * @author 7d14
 *
 */
@Service
@RequiredArgsConstructor
public class UserListServiceImpl implements UserListService {

	/** ユーザー情報テーブルDAO */
	private final UserInfoRepository repository;

	/** Dozer Mapper */
	private final Mapper mapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UserListInfo> editUserList() {
		return toUserListInfos(repository.findAll());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UserListInfo> editUserListByParam(UserSearchInfo dto) {
		return toUserListInfos(findUserInfoByParam(dto));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DeleteResult deleteUserInfoById(String loginId) {
		Optional<UserInfo> userInfo = repository.findByLoginId(loginId);
		if (userInfo.isEmpty()) {
			return DeleteResult.USER_ERROR;
		}

		repository.deleteByLoginId(loginId);
		return DeleteResult.USER_SUCCEED;
	}

	/**
	 * ユーザー情報の条件検索を行い、検索結果を返却します。
	 * 
	 * @param form 入力情報
	 * @return 検索結果
	 */
	private List<UserInfo> findUserInfoByParam(UserSearchInfo dto) {
		String loginIdParam = AppUtil.addWildcard(dto.getLoginId());

		if (dto.getUserStatusKind() != null && dto.getAuthorityKind() != null) {
			return repository.findByLoginIdLikeAndUserStatusKindAndAuthorityKind(loginIdParam,
					dto.getUserStatusKind(), dto.getAuthorityKind());
		} else if (dto.getUserStatusKind() != null) {
			return repository.findByLoginIdLikeAndUserStatusKind(loginIdParam, dto.getUserStatusKind());
		} else if (dto.getAuthorityKind() != null) {
			return repository.findByLoginIdLikeAndAuthorityKind(loginIdParam, dto.getAuthorityKind());
		} else {
			return repository.findByLoginIdLike(loginIdParam);
		}
	}

	/**
	 * ユーザー情報EntityのListをユーザー一覧情報DTOのListに変換します。
	 * 
	 * @param userInfos ユーザー情報EntityのList
	 * @return ユーザ一覧情報DTOのList
	 */
	private List<UserListInfo> toUserListInfos(List<UserInfo> userInfos) {
		var userListInfos = new ArrayList<UserListInfo>();
		for (UserInfo userInfo : userInfos) {
			UserListInfo userListInfo = mapper.map(userInfo, UserListInfo.class);
			((UserListInfo) userListInfo).setStatus(userInfo.getUserStatusKind().getDisplayValue());
			((UserListInfo) userListInfo).setAuthority(userInfo.getAuthorityKind().getDisplayValue());
			userListInfos.add((UserListInfo) userListInfo);
		}

		return userListInfos;

	}

	

}