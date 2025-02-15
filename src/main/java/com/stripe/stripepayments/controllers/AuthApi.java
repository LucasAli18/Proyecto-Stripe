package com.stripe.stripepayments.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stripe.stripepayments.common.constants.ApiPathContants;
import com.stripe.stripepayments.common.dto.AuthResponseDto;
import com.stripe.stripepayments.common.dto.UserRequest;

import jakarta.validation.Valid;

@RequestMapping(ApiPathContants.V1_ROUTE + ApiPathContants.AUTH_ROUTE)
public interface AuthApi {
	@PostMapping
	ResponseEntity<AuthResponseDto> createUser(@RequestBody @Valid UserRequest userRequest);
}
