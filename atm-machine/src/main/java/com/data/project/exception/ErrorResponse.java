package com.data.project.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

public class ErrorResponse {

	
	private HttpStatus status;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyyMMdd hh:mm:ss")
	private LocalDateTime timestamp;
	
	private String message;

	public ErrorResponse(HttpStatus status) {
		this.status = status;
	}
	
	public ErrorResponse() {
		// TODO Auto-generated constructor stub
	
		timestamp = LocalDateTime.now();
	
	}


	public ErrorResponse(HttpStatus status, String message) {
		this();
		this.status = status;
		this.message = message;
	}

	public ErrorResponse(HttpStatus status, LocalDateTime timestamp, String message) {
		super();
		this.status = status;
		this.timestamp = timestamp;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
}
