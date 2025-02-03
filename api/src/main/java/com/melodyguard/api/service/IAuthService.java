package com.melodyguard.api.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;

import com.melodyguard.api.dto.request.LoginDto;
import com.melodyguard.api.dto.request.UserDtoReq;
import com.melodyguard.api.dto.response.DeleteResp;
import com.melodyguard.api.dto.response.UserDtoResp;
import com.melodyguard.api.model.AuthToken;

public interface IAuthService {
	UserDtoResp registerHandler(UserDtoReq user);

	public AuthToken loginHandler(@RequestBody LoginDto body, HttpServletResponse resp);

	public DeleteResp logoutHandler(HttpServletResponse resp);
}
