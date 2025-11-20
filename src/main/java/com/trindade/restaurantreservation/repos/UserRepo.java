package com.trindade.restaurantreservation.repos;

import com.trindade.restaurantreservation.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String> {
	Optional<User> findByEmail(String email);
}
