package com.trindade.restaurantreservation.dtos;

import com.trindade.restaurantreservation.domain.user.UserRole;

public record LoginResponseDTO(
		String name,
		String email,
		UserRole role,
		String token
) {}