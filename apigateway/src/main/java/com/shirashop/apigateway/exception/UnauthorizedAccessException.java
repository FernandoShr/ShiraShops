package com.shirashop.apigateway.exception;

public class UnauthorizedAccessException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public UnauthorizedAccessException(String msg) {
		super(msg);
	}
	
}
