package com.trindade.restaurantreservation.repos;

import com.trindade.restaurantreservation.domain.table.RestaurantTable;
import com.trindade.restaurantreservation.domain.table.TableState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantTableRepo extends JpaRepository<RestaurantTable, String> {
	long countByStatus(TableState status);
}
