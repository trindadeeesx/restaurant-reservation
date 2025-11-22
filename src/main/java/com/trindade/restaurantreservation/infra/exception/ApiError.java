package com.trindade.restaurantreservation.infra.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ApiError {
	private String message;
	private int status;
	private String error;
	private LocalDateTime timestamp;
	private String path;
	private List<String> fields;

	public ApiError(String message, int status, String error, LocalDateTime timestamp, String path) {
		this.message = message;
		this.status = status;
		this.error = error;
		this.timestamp = timestamp;
		this.path = path;
	}

	public ApiError(String message, int status, String error, LocalDateTime timestamp, String path, List<String> fields) {
		this.message = message;
		this.status = status;
		this.error = error;
		this.timestamp = timestamp;
		this.path = path;
		this.fields = fields;
	}
}
