package com.shirashop.userapi.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.shirashop.userapi.exceptions.EntityNotFoundException;
import com.shirashop.userapi.exceptions.InvalidEntityException;

@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> entityNotFound(EntityNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidEntityException.class)
	public ResponseEntity<String> invalidEntity(InvalidEntityException e) {
		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> nullPointer(NullPointerException e) {
		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	
}
