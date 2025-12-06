package com.trindade.restaurantreservation.controllers;

import com.trindade.restaurantreservation.domain.table.RestaurantTable;
import com.trindade.restaurantreservation.dtos.RestaurantTableRequestDTO;
import com.trindade.restaurantreservation.repos.RestaurantTableRepo;
import com.trindade.restaurantreservation.services.RestaurantTableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant-tables")
@RequiredArgsConstructor
public class TablesController {
	/**
	 * GET /mesas — Lista todas as mesas e seus status.
	 * 			POST /mesas — Adiciona uma nova mesa (apenas administradores).
	 * 	PATCH /mesas/:id — Atualiza informações de uma mesa.
	 * 			DELETE /mesas/:id — Remove uma mesa (apenas administradores).
	 */

	private final RestaurantTableService service;

	@GetMapping("/")
	public ResponseEntity<?> getOne() {
		List<RestaurantTable> tables = this.service.getAll();

		return ResponseEntity.status(HttpStatus.OK).body(tables);
	}

	@PostMapping("/")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> create(@Valid @RequestBody RestaurantTableRequestDTO dto) {
		var table = this.service.create(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(table);
	}

//	@PatchMapping("/{id}")
//	public ResponseEntity<?> update(
//			@PathVariable Long id,
//			@RequestBody UpdateTableDTO updateData
//	) {
//		var table = this.service.updateTable(id, updateData);
//		return ResponseEntity.ok(table);
//	}
//
//	@DeleteMapping("/{id}")
//	@PreAuthorize("hasRole('ADMIN')")
//	public ResponseEntity<?> delete(@PathVariable Long id) {
//		this.service.deleteTable(id);
//		return ResponseEntity.noContent().build();
//	}
}
