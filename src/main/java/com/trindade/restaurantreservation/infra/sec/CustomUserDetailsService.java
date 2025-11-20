package com.trindade.restaurantreservation.infra.sec;

import com.trindade.restaurantreservation.domain.user.User;
import com.trindade.restaurantreservation.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Serviço personalizado para carregar os detalhes do usuário a partir do repositório.
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepo userRepo;

	/**
	 * Carrega os detalhes do usuário com base no nome de usuário (email).
	 *
	 * @param username O email do usuário.
	 * @return Os detalhes do usuário.
	 * @throws UsernameNotFoundException Se o usuário não for encontrado.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepo.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + username));

		return new org.springframework.security.core.userdetails.User(
				user.getEmail(),
				user.getPassword(),
				new ArrayList<>()
		);
	}
}
