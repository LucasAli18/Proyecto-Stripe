package com.stripe.stripepayments.strategy.impl;

import java.util.Optional;
import org.springframework.stereotype.Component;

import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.stripepayments.common.entities.Payment;
import com.stripe.stripepayments.common.enums.StripeEventEnum;import com.stripe.stripepayments.repositories.PaymentRepository;
import com.stripe.stripepayments.strategy.StripeStrategy;

@Component
public class StripeStrategyPaymentIntentSucceedImpl implements StripeStrategy{

	private final PaymentRepository paymentRepository;
	
	public StripeStrategyPaymentIntentSucceedImpl(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	@Override
	public boolean isApplicable(Event event) {
		System.out.println("Es correcto el evento?");
		return StripeEventEnum.PAYMENT_INTENT_SUCCEED.value.equals(event.getType());
	}

	@Override
	public Event process(Event event) {
		System.out.println("Procesando el evento");
		return Optional.of(event)
				.map(this::deserialize)
				.map(this::mapToEntity)
				.map(paymentRepository::save)
				.map(given -> event)
				.orElseThrow(()-> new RuntimeException("Error processing"));
	}

	private PaymentIntent deserialize(Event event) {
		return (PaymentIntent) event.getDataObjectDeserializer().getObject()
				.orElseThrow(() -> new RuntimeException("Error deserializing object"));
	}

	private Payment mapToEntity(PaymentIntent paymentIntent) {
		return Payment.builder()
				.paymentIntentId(paymentIntent.getId())
				.customerId(paymentIntent.getCustomer())
				.amount(paymentIntent.getAmount())
				.currency(paymentIntent.getCurrency())
				.type(StripeEventEnum.PAYMENT_INTENT_SUCCEED)
				.build();
	}
	
}
