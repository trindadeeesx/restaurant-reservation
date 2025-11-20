package com.trindade.restaurantreservation.infra.sec;

import com.trindade.restaurantreservation.domain.user.User;
import com.trindade.restaurantreservation.repos.UserRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecFilter extends OncePerRequestFilter {
	@Autowired
	private Token tokenService;

	@Autowired
	private UserRepo userRepo;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
	                                HttpServletResponse response,
	                                FilterChain filterChain
	) throws IOException, ServletException {
		var token = this.recoverToken(request);
		var isValid = tokenService.validateToken(token);

		if (isValid != null) {
			User user = userRepo.findByEmail(isValid).orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));

			var authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
			var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);

			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);
	}

	private String recoverToken(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");
		if (authHeader == null) return null;
		return authHeader.replace("Bearer ", "");
	}
}
