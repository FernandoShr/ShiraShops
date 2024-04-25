package com.shirashop.pedidoapi.exceptions;

public class InvalidFieldException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public InvalidFieldException(String msg) {
		super(msg);
	}
	
}
