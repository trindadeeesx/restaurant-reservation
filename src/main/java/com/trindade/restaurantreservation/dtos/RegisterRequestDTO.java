package com.trindade.restaurantreservation.dtos;

import com.trindade.restaurantreservation.domain.user.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(

		@NotBlank(message = "Nome é obrigatório.")
		@Size(min = 3, max = 50, message = "Nome deve ter entre 3 e 50 caracteres.")
		String name,

		@NotBlank(message = "Email é obrigatório.")
		@Email(message = "Email inválido.")
		String email,

		@NotBlank(message = "Senha é obrigatória.")
		@Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres.")
		String password,

		// opcional
		UserRole role
) {}