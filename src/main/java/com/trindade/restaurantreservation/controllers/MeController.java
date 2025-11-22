package com.trindade.restaurantreservation.controllers;

import com.trindade.restaurantreservation.domain.user.User;
import com.trindade.restaurantreservation.dtos.MeResponseDTO;
import com.trindade.restaurantreservation.services.MeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/me")
@RequiredArgsConstructor
public class MeController {
	private final MeService meService;

	@GetMapping
	public ResponseEntity<MeResponseDTO> getMe(@AuthenticationPrincipal User user) {
		return ResponseEntity.ok(meService.me(user));
	}
}
