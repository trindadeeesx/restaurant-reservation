package com.trindade.restaurantreservation.infra.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ApiError> handleBusinessException(
			BusinessException ex, HttpServletRequest req
	) {
		ApiError error = new ApiError(
				ex.getMessage(),
				HttpStatus.BAD_REQUEST.value(),
				"BusinessException",
				LocalDateTime.now(),
				req.getRequestURI()
		);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidationException(
			MethodArgumentNotValidException ex,
			HttpServletRequest req
	) {
		Map<String, String> errors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.collect(
						Collectors.toMap(
								f -> f.getField(),
								f -> f.getDefaultMessage(),
								(a, b) -> a // evita conflito caso a key seja a mesma
						)
				);

		ApiError error = new ApiError(
				"Campos inválidos",
				HttpStatus.BAD_REQUEST.value(),
				"ValidationException",
				LocalDateTime.now(),
				req.getRequestURI(),
				errors
		);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleGenericException(
			Exception ex, HttpServletRequest req
	) {
		ApiError error = new ApiError(
				ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Exception",
				LocalDateTime.now(),
				req.getRequestURI()
		);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
}
