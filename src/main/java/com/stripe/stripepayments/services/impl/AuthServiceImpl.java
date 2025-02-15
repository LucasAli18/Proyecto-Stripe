package com.stripe.stripepayments.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.stripe.stripepayments.common.dto.AuthResponseDto;
import com.stripe.stripepayments.common.dto.UserRequest;
import com.stripe.stripepayments.common.entities.UserModel;
import com.stripe.stripepayments.repositories.UserRepository;
import com.stripe.stripepayments.services.AuthService;
import com.stripe.stripepayments.services.StripeService;
@Service
public class AuthServiceImpl implements AuthService{
	private final StripeService stripeService;
	private final UserRepository userRepository;
	
	public AuthServiceImpl(StripeService stripeService, UserRepository userRepository) {
		this.stripeService = stripeService;
		this.userRepository = userRepository;
	}

	@Override
	public AuthResponseDto createUser(UserRequest userRequest) {
		
		return Optional.of(userRequest)
				.map(this::mapToEntity)
				.map(this::setUserCustomerAndProduct)
				.map(userRepository::save)
				.map(userModel -> AuthResponseDto.builder()
						.customerId(userModel.getCustomerId())
						.productId(userModel.getProductoId())
						.build())
				.orElseThrow(()-> new RuntimeException("Error creating user"));
	}

	private UserModel mapToEntity(UserRequest userRequest) {
		return UserModel.builder()
				.name(userRequest.getName())
				.email(userRequest.getEmail())
				.password(userRequest.getPassword())
				.build();
	}
	
	private UserModel setUserCustomerAndProduct(UserModel userModel) {
		var customerCreated = stripeService.createCustomer(userModel.getEmail());
		var productCreated = stripeService.createProduct(userModel.getName());
		stripeService.createPrice(productCreated.getId());
		
		userModel.setProductoId(productCreated.getId());
		userModel.setCustomerId(customerCreated.getId());
		return userModel;
	}
}
