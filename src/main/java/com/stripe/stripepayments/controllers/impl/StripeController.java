package com.stripe.stripepayments.controllers.impl;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.stripepayments.common.dto.CheckoutRequest;
import com.stripe.stripepayments.common.dto.CheckoutResponse;
import com.stripe.stripepayments.controllers.StripeApi;
import com.stripe.stripepayments.services.StripeService;

import jakarta.validation.Valid;
@RestController
public class StripeController implements StripeApi{
	private final StripeService stripeService;
	 
	public StripeController(StripeService stripeService) {
		this.stripeService = stripeService;
	}
	@Override
	public ResponseEntity<Void> stripeWebhook(String payload, String stripeHeader) {
		System.out.println("EMPEZANDO STRPE WEBHOOK");
		var event = stripeService.constructEvent(payload, stripeHeader);
		stripeService.manageWebhook(event);
		return ResponseEntity.noContent().build();
	}
	@Override
	public ResponseEntity<CheckoutResponse> createCheckout(@Valid CheckoutRequest checkoutRequest) {
		return ResponseEntity.ok(stripeService.createCheckout(checkoutRequest));
	}

}
