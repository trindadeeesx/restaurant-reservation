package com.trindade.restaurantreservation.controllers;

import com.trindade.restaurantreservation.dtos.LoginRequestDTO;
import com.trindade.restaurantreservation.dtos.LoginResponseDTO;
import com.trindade.restaurantreservation.dtos.RegisterRequestDTO;
import com.trindade.restaurantreservation.dtos.RegisterResponseDTO;
import com.trindade.restaurantreservation.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService service;

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO body) {
		RegisterResponseDTO res = this.service.register(body);
		return ResponseEntity.status(HttpStatus.CREATED).body(res);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO body) {
		LoginResponseDTO res = this.service.login(body);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
}
