package com.trindade.restaurantreservation.dtos;

import com.trindade.restaurantreservation.domain.user.UserRole;

public record RegisterResponseDTO(
		String name,
		String email,
		UserRole role,
		String token
) {}
