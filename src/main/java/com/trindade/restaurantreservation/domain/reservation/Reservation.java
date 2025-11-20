package com.trindade.restaurantreservation.domain.reservation;

import com.trindade.restaurantreservation.domain.table.RestaurantTable;
import com.trindade.restaurantreservation.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "reservations")
@Table(name = "reservations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "table_id", nullable = false)
	private RestaurantTable table;

	@NotNull
	@FutureOrPresent
	@Column(name = "reservation_date", nullable = false)
	private LocalDateTime reservationDate;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ReservationState status = ReservationState.ACTIVE;
}
