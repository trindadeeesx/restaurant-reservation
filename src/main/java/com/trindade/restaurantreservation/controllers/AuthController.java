package com.trindade.restaurantreservation.controllers;

import com.trindade.restaurantreservation.domain.user.User;
import com.trindade.restaurantreservation.domain.user.UserRole;
import com.trindade.restaurantreservation.dtos.LoginRequestDTO;
import com.trindade.restaurantreservation.dtos.LoginResponseDTO;
import com.trindade.restaurantreservation.dtos.RegisterRequestDTO;
import com.trindade.restaurantreservation.dtos.RegisterResponseDTO;
import com.trindade.restaurantreservation.infra.sec.Token;
import com.trindade.restaurantreservation.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final UserRepo userRepo;
	private final PasswordEncoder passwordEncoder;
	private final Token tokenService;

	@PostMapping("/register")
	public ResponseEntity register(@RequestBody RegisterRequestDTO body) {
		Optional<User> user = userRepo.findByEmail(body.email());

		if (user.isPresent()) {
			return ResponseEntity.status(400).body("Usuário já existe");
		}

		User newUser = new User();
		newUser.setName(body.name());
		newUser.setEmail(body.email());
		newUser.setPassword(passwordEncoder.encode(body.password()));
		newUser.setRole(UserRole.CUSTOMER);
		userRepo.save(newUser);

		String token = tokenService.generateToken(newUser);
		return ResponseEntity.status(201).body(new RegisterResponseDTO(newUser.getName(), newUser.getEmail(), token));
	}

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginRequestDTO body) {
		User user = userRepo.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

		if (user == null || !passwordEncoder.matches(body.password(), user.getPassword())) {
			return ResponseEntity.status(401).body("Credenciais inválidas");
		}

		String token = tokenService.generateToken(user);
		return ResponseEntity.ok(new LoginResponseDTO(user.getName(), user.getEmail(), token));
	}
}
