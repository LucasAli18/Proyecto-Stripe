package com.stripe.stripepayments.strategy.impl;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.stripepayments.common.entities.Payment;
import com.stripe.stripepayments.common.enums.StripeEventEnum;
import com.stripe.stripepayments.repositories.PaymentRepository;
import com.stripe.stripepayments.strategy.StripeStrategy;

@Component
public class StripeStrategyCheckoutSessionCompleted implements StripeStrategy{

	private final PaymentRepository paymentRepository;
	
	public StripeStrategyCheckoutSessionCompleted(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	@Override
	public boolean isApplicable(Event event) {
		return StripeEventEnum.CHECKOUT_SESSION_COMPLETED.value.equals(event.getType());
	}

	@Override
	public Event process(Event event) {
		System.out.println("PROCESS CHECKOUT");
		var session = this.desrialize(event);
		return Optional.of(event)
				.map(givenEvent -> paymentRepository.findByPaymentIntentId(session.getPaymentIntent()))
				.map(payment -> setProductId(payment, session.getMetadata().get("product_id")))
				.map(given -> event)
				.orElseThrow(()-> new RuntimeException("Error processing"));
	}
	
	private Payment setProductId(Payment payment, String productId) {
		System.out.println("SETPRODUCT CHECKOUT");
		payment.setProductoId(productId);
		payment.setType(StripeEventEnum.CHECKOUT_SESSION_COMPLETED);
		return payment;
	}

	private Session desrialize(Event event) {
		return (Session) event.getDataObjectDeserializer().getObject()
				.orElseThrow(() -> new RuntimeException("Error deserializing"));
	}
	
}
