package com.stripe.stripepayments.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stripe.stripepayments.common.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
	Payment findByPaymentIntentId(String paymentId);
}
