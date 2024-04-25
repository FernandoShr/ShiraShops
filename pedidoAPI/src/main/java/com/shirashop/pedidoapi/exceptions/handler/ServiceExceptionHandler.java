package com.shirashop.pedidoapi.exceptions.handler;

import com.shirashop.pedidoapi.exceptions.EntityNotFoundException;
import com.shirashop.pedidoapi.exceptions.InvalidFieldException;
import com.shirashop.pedidoapi.exceptions.OutOfStockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.shirashop.pedidoapi.exceptions.EmptyListException;

import feign.FeignException.FeignClientException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ServiceExceptionHandler {
	
	@ExceptionHandler(FeignClientException.class)
	public ResponseEntity<String> feignClientNotFound(FeignClientException e){
		log.warn("[ExceptionHandler] : feignClientNotFound");
		return new ResponseEntity<String>(e.contentUTF8(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> entityNotFound(EntityNotFoundException e) {
		log.warn("[ExceptionHandler] : entityNotFound");
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmptyListException.class)
	public ResponseEntity<String> emptyList(EmptyListException e) {
		log.warn("[ExceptionHandler] : emptyList");
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(OutOfStockException.class)
	public ResponseEntity<String> outOfStock(OutOfStockException e) {
		log.warn("[ExceptionHandler] : outOfStock");
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidFieldException.class)
	public ResponseEntity<String> invalidField(InvalidFieldException e) {
		log.warn("[ExceptionHandler] : invalidField");
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}
	
}
