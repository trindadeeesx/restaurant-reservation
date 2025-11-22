package com.trindade.restaurantreservation.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(
		@NotBlank(message = "Email é obrigatório.")
		@Email(message = "Email inválido.")
		String email,

		@NotBlank(message = "Senha é obrigatória.")
		String password
) {}
