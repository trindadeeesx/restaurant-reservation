package com.trindade.restaurantreservation.infra.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class ApiError {
	private String message;
	private int status;
	private String error;
	private LocalDateTime timestamp;
	private String path;
	private Map<String, String> errors;

	public ApiError(String message, int status, String error, LocalDateTime timestamp, String path) {
		this.message = message;
		this.status = status;
		this.error = error;
		this.timestamp = timestamp;
		this.path = path;
	}

	public ApiError(String message, int status, String error, LocalDateTime timestamp, String path, Map<String, String> errors) {
		this.message = message;
		this.status = status;
		this.error = error;
		this.timestamp = timestamp;
		this.path = path;
		this.errors = errors;
	}
}
