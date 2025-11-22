package com.trindade.restaurantreservation.dtos;

import com.trindade.restaurantreservation.domain.table.TableState;

public record RestaurantTableResponseDTO(
		String name,
		Integer capacity,
		TableState status
)
{}
