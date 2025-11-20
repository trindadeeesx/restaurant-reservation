package com.trindade.restaurantreservation.repos;

import com.trindade.restaurantreservation.domain.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepo extends JpaRepository<Reservation, String> {}
