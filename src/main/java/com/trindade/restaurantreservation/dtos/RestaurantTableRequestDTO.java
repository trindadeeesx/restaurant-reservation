package com.trindade.restaurantreservation.dtos;

import com.trindade.restaurantreservation.domain.table.TableState;
import jakarta.validation.constraints.*;

public record RestaurantTableRequestDTO(

		@NotBlank(message = "A mesa precisa de um nome ou número.")
		String name,

		@NotNull(message = "A capacidade é obrigatória.")
		@Min(value = 1, message = "A capacidade mínima é 1")
		@Max(value = 16, message = "A capacidade máxima é 16")
		Integer capacity,

		@NotNull(message = "O estado da mesa é obrigatório.")
		TableState status
) {}
