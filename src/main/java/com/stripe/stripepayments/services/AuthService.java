package com.stripe.stripepayments.services;

import com.stripe.stripepayments.common.dto.AuthResponseDto;
import com.stripe.stripepayments.common.dto.UserRequest;

public interface AuthService {
	AuthResponseDto createUser(UserRequest userRequest);
}
