package com.stripe.stripepayments.common.dto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserRequest {
	@NotNull
	private String email;
	@NotNull
	private String password;
	@NotNull
	private String name;
}
