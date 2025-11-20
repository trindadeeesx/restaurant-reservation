package com.trindade.restaurantreservation.infra.sec;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.trindade.restaurantreservation.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class Token {
	@Value("${api.infra.sec.token.secret}")
	private String algorithmSecret;

	public String generateToken(User user) {
		try {
			Algorithm alg = Algorithm.HMAC256(algorithmSecret);

			return JWT.create()
					.withIssuer("restaurant-reservation")
					.withSubject(user.getEmail())
					.withExpiresAt(generateExpirationDate())
					.sign(alg);
		} catch (JWTCreationException exc) {
			throw new RuntimeException("Erro ao gerar JWT", exc);
		}
	}

	public String validateToken(String token) {
		try {
			Algorithm alg = Algorithm.HMAC256(algorithmSecret);

			return JWT.require(alg)
					.withIssuer("restaurant-reservation")
					.build()
					.verify(token)
					.getSubject();
		} catch (JWTVerificationException exception) {
			return null;
		}
	}

	private Instant generateExpirationDate() {
		// Token válido por 12 horas
		return LocalDateTime.now().plusHours(12).toInstant(ZoneOffset.of("-03:00")); // São Paulo
	}

}
