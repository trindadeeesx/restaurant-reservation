package com.trindade.restaurantreservation.domain.table;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "restaurant_table")
@Table(name = "restaurant_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantTable {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@NotNull
	private String name;

	@NotNull
	@Min(1)
	@Max(100000)
	@Column(nullable = false)
	private Integer capacity;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(nullable = false)
	private TableState status;
}
