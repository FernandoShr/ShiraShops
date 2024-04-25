package com.shirashop.apigateway.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import com.shirashop.apigateway.exception.MissingTokenException;
import com.shirashop.apigateway.exception.UnauthorizedAccessException;

@ControllerAdvice
public class GatewayHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(UnauthorizedAccessException.class)
	public ResponseEntity<String> unathorizedAccess(UnauthorizedAccessException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(MissingTokenException.class)
	public ResponseEntity<String> missingToken(MissingTokenException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
	}
	
	
}
