package com.melodyguard.api.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.melodyguard.api.dto.request.LoginDto;
import com.melodyguard.api.dto.request.UserDtoReq;
import com.melodyguard.api.dto.response.DeleteResp;
import com.melodyguard.api.service.IAuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private IAuthService authService;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody @Valid UserDtoReq user) {
		return ResponseEntity.ok(authService.registerHandler(user));
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid LoginDto body, HttpServletResponse resp) {
		return ResponseEntity.ok(authService.loginHandler(body, resp));
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletResponse resp) {
		DeleteResp message = authService.logoutHandler(resp);
		return ResponseEntity.ok(message);
	}
}
