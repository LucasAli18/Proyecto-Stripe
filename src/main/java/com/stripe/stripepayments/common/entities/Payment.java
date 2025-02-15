package com.stripe.stripepayments.common.entities;

import com.stripe.stripepayments.common.enums.StripeEventEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String paymentIntentId;
	private String customerId;
	private String productoId;
	private Long amount;
	private String currency;
	@Enumerated(EnumType.STRING)
	private StripeEventEnum type;
}
