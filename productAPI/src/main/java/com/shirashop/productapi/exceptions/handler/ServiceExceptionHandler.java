package com.shirashop.productapi.exceptions.handler;

import com.shirashop.productapi.exceptions.InvalidEntityException;
import com.shirashop.productapi.exceptions.OutOfStockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.shirashop.productapi.exceptions.EntityNotFoundException;

@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> entityNotFound(EntityNotFoundException e) {
		System.out.println("EntityNotFoundException - não foi possível realizar a busca");
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidEntityException.class)
	public ResponseEntity<String> invalidEntity(InvalidEntityException e) {
		System.out.println("InvalidEntityException - Entidade com campo inválido");
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> nullPointer(NullPointerException e){
		System.out.println("NullPointer - ExceptionHandler");
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(OutOfStockException.class)
	public ResponseEntity<String> outOfStock(OutOfStockException e) {
		System.out.println("[ExceptionHandler] : outOfStock");
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}
}
