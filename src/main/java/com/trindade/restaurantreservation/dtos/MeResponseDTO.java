package com.trindade.restaurantreservation.dtos;

import com.trindade.restaurantreservation.domain.user.UserRole;

public record MeResponseDTO(
		String name,
		String email,
		UserRole role
) {}
