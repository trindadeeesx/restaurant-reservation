package com.trindade.restaurantreservation.services;

import com.trindade.restaurantreservation.domain.user.User;
import com.trindade.restaurantreservation.dtos.MeResponseDTO;
import com.trindade.restaurantreservation.infra.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeService {
	public MeResponseDTO me(User user) {
		if (user == null) {
			throw new BusinessException("Usuario não autenticado.");
		}

		return new MeResponseDTO(user.getName(), user.getName(), user.getRole());
	}
}
