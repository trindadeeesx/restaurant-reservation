package com.trindade.restaurantreservation.controllers;

import com.trindade.restaurantreservation.domain.reservation.ReservationState;
import com.trindade.restaurantreservation.domain.table.TableState;
import com.trindade.restaurantreservation.repos.ReservationRepo;
import com.trindade.restaurantreservation.repos.RestaurantTableRepo;
import com.trindade.restaurantreservation.repos.UserRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/metrics")
public class MetricsController {

	private final UserRepo userRepository;
	private final RestaurantTableRepo tableRepository;
	private final ReservationRepo reservationRepository;

	public MetricsController(
			UserRepo userRepository,
			RestaurantTableRepo tableRepository,
			ReservationRepo reservationRepository
	) {
		this.userRepository = userRepository;
		this.tableRepository = tableRepository;
		this.reservationRepository = reservationRepository;
	}

	@GetMapping
	public ResponseEntity<?> getMetrics() {

		long totalUsers = userRepository.count();
		long totalTables = tableRepository.count();
		long availableTables = tableRepository.countByStatus(TableState.AVAILABLE);
		long reservedTables = tableRepository.countByStatus(TableState.RESERVED);

		long activeReservations = reservationRepository.countByStatus(ReservationState.ACTIVE);

		Map<String, Object> metrics = new HashMap<>();
		metrics.put("totalUsers", totalUsers);
		metrics.put("totalTables", totalTables);
		metrics.put("availableTables", availableTables);
		metrics.put("reservedTables", reservedTables);
		metrics.put("activeReservations", activeReservations);

		return ResponseEntity.ok(metrics);
	}
}
