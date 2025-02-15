package com.stripe.stripepayments.common.dto;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AuthResponseDto {
	private String customerId;
	private String productId;
}
