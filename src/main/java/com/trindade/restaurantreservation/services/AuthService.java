package com.trindade.restaurantreservation.services;

import com.trindade.restaurantreservation.domain.user.User;
import com.trindade.restaurantreservation.domain.user.UserRole;
import com.trindade.restaurantreservation.dtos.LoginRequestDTO;
import com.trindade.restaurantreservation.dtos.LoginResponseDTO;
import com.trindade.restaurantreservation.dtos.RegisterRequestDTO;
import com.trindade.restaurantreservation.dtos.RegisterResponseDTO;
import com.trindade.restaurantreservation.infra.exception.BusinessException;
import com.trindade.restaurantreservation.infra.sec.Token;
import com.trindade.restaurantreservation.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final UserRepo repo;
	private final PasswordEncoder passwordEncoder;
	private final Token tokenService;

	public RegisterResponseDTO register(RegisterRequestDTO dto) {
		// verifica se nao existe o email no banco de dados
		if (repo.findByEmail(dto.email()).isPresent()) {
			throw new BusinessException("Usuário já existente");
		}

		// cria novo usuario
		User user = new User();
		user.setName(dto.name());
		user.setEmail(dto.email());
		user.setPassword(passwordEncoder.encode(dto.password()));
		user.setRole(
				dto.role() != null ? dto.role() : UserRole.CUSTOMER
		); // garantir role

		repo.save(user); // salva no banco

		String token = tokenService.generateToken(user); // cria o token com base no email do usuario

		return new RegisterResponseDTO(user.getName(), user.getEmail(), user.getRole(), token);
	}

	public LoginResponseDTO login(LoginRequestDTO dto) {
		User user = repo.findByEmail(dto.email())
				.orElseThrow(() -> new BusinessException("Credenciais inválidas"));

		if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
			throw new BusinessException("Credenciais inválidas");
		}

		String token = tokenService.generateToken(user);
		return new LoginResponseDTO(user.getName(), user.getEmail(), user.getRole(), token);
	}
}
