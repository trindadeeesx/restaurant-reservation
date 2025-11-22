package com.trindade.restaurantreservation.dtos;

public record RegisterResponseDTO(
		String name,
		String email,
		String token
) {}
