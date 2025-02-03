package com.melodyguard.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.melodyguard.api.dto.request.RoleDto;
import com.melodyguard.api.dto.response.UserDtoResp;
import com.melodyguard.api.model.User;

@Service
public interface IUserService {
	User getById(String id);

	List<UserDtoResp> getAll(Integer page);

	void assignRole(String id, RoleDto role);

}
