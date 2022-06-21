package com.data.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AccountsAdvice {

	@ResponseBody
	@ExceptionHandler(AccountInvalidException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> accountNoNotFoundHandler(AccountInvalidException ex) {
		
		
		return buildResponseEntity(new ErrorResponse(HttpStatus.NOT_FOUND,ex.getMessage()));
	}
	
	
	@ResponseBody
	@ExceptionHandler(PINInvalidException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> pinNotFoundHandler(PINInvalidException ex) {
		
		return buildResponseEntity(new ErrorResponse(HttpStatus.NOT_FOUND,ex.getMessage()));
	}
	
	@ResponseBody
	@ExceptionHandler(AtmAmountLimitException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public ResponseEntity<Object> atmWithrawalLimitHandler(AtmAmountLimitException ex) {
		
		return buildResponseEntity(new ErrorResponse(HttpStatus.NOT_ACCEPTABLE,ex.getMessage()));
	}
	
	
	@ResponseBody
	@ExceptionHandler(AccountBalanceLimitException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public ResponseEntity<Object> accountBalanceLimitHandler(AccountBalanceLimitException ex) {
		
		return buildResponseEntity(new ErrorResponse(HttpStatus.NOT_ACCEPTABLE,ex.getMessage()));
	}
	
	@ResponseBody
	@ExceptionHandler(InvalidDispenseAmountException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public ResponseEntity<Object> invalidAmountHandler(InvalidDispenseAmountException ex) {
		
		return buildResponseEntity(new ErrorResponse(HttpStatus.NOT_ACCEPTABLE,ex.getMessage()));
	}
	
	
	private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse)
	{
		return new ResponseEntity<Object>(errorResponse, errorResponse.getStatus());
	}
	
}
