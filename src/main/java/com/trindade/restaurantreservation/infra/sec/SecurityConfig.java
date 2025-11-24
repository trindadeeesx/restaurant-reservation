package com.trindade.restaurantreservation.infra.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuração de segurança para a aplicação.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private SecFilter secFilter;

	/**
	 * Configura o filtro de segurança e as regras de autorização.
	 *
	 * @param http O objeto HttpSecurity para configurar a segurança HTTP.
	 * @return A cadeia de filtros de segurança configurada.
	 * @throws Exception Se ocorrer um erro durante a configuração.
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			http
					.csrf(AbstractHttpConfigurer::disable)
					.cors(cors -> {
					})
					.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					.authorizeHttpRequests(auth -> auth
							// Libera endpoints de autenticação
							.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
							.requestMatchers(HttpMethod.POST, "/auth/register").permitAll()

							// Libera H2 Console
							.requestMatchers("/h2-console/**").permitAll()

							// Demais exigem token
							.anyRequest().authenticated()
					)

					// Necessário para carregar H2 no navegador
					.headers(headers ->
							headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
					)

					// Filtro JWT
					.addFilterBefore(secFilter, UsernamePasswordAuthenticationFilter.class);

			return http.build();
		}

		/**
		 * Configura o codificador de senhas usando BCrypt.
		 *
		 * @return O codificador de senhas.
		 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Configura o gerenciador de autenticação.
	 *
	 * @param authenticationConfiguration A configuração de autenticação.
	 * @return O gerenciador de autenticação.
	 * @throws Exception Se ocorrer um erro durante a configuração.
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
