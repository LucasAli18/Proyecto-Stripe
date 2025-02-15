package com.stripe.stripepayments.controllers.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.stripepayments.common.dto.AuthResponseDto;
import com.stripe.stripepayments.common.dto.UserRequest;
import com.stripe.stripepayments.controllers.AuthApi;
import com.stripe.stripepayments.services.AuthService;

import jakarta.validation.Valid;
@RestController
public class AuthController implements AuthApi{

	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@Override
	public ResponseEntity<AuthResponseDto> createUser(@Valid UserRequest userRequest) {
		System.out.println("Empezando createrUser");
		return ResponseEntity.ok(authService.createUser(userRequest));
	}
	
}
