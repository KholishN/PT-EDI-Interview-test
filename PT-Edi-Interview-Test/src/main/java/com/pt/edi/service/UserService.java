package com.pt.edi.service;

import com.pt.edi.dto.DtoResponse;
import com.pt.edi.entity.UserEntity;

public interface UserService {
	DtoResponse<UserEntity> getDataUser(String userId);

	DtoResponse<UserEntity> setDataUser(UserEntity param);

	DtoResponse<UserEntity> delDataUser(String userId);
}
