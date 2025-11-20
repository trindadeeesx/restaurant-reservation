package com.trindade.restaurantreservation.repos;

import com.trindade.restaurantreservation.domain.table.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantTableRepo extends JpaRepository<RestaurantTable, String> {}
