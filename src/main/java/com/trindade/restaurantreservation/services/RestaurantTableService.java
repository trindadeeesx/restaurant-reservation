package com.trindade.restaurantreservation.services;

import com.trindade.restaurantreservation.domain.table.RestaurantTable;
import com.trindade.restaurantreservation.domain.table.TableState;
import com.trindade.restaurantreservation.dtos.RestaurantTableRequestDTO;
import com.trindade.restaurantreservation.dtos.RestaurantTableResponseDTO;
import com.trindade.restaurantreservation.repos.RestaurantTableRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantTableService {
	private final RestaurantTableRepo repo;

	public RestaurantTableResponseDTO create(RestaurantTableRequestDTO dto) {
		RestaurantTable restTable = new RestaurantTable();
		restTable.setName(dto.name());
		restTable.setCapacity(dto.capacity());
		restTable.setStatus(
				dto.status() != null ? dto.status() : TableState.INACTIVE
		);

		this.repo.save(restTable);

		return new RestaurantTableResponseDTO(restTable.getName(), restTable.getCapacity(), restTable.getStatus());
	}
}
