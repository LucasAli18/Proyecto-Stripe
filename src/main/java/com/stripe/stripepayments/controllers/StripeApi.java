package com.stripe.stripepayments.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stripe.stripepayments.common.constants.ApiPathContants;
import com.stripe.stripepayments.common.dto.CheckoutRequest;
import com.stripe.stripepayments.common.dto.CheckoutResponse;

import jakarta.validation.Valid;

@RequestMapping(ApiPathContants.V1_ROUTE + ApiPathContants.STRIPE_ROUTE)
public interface StripeApi {

	@PostMapping(value = "/webhook")
	ResponseEntity<Void> stripeWebhook(
			@RequestBody String payload, 
			@RequestHeader("Stripe-Signature") String stripeHeader
			);
	
	@PostMapping(value = "/checkout")
	ResponseEntity<CheckoutResponse> createCheckout(@RequestBody @Valid CheckoutRequest checkoutRequest);

}