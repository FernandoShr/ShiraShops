package com.shirashop.userapi.exceptions;

public class InvalidEntityException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public InvalidEntityException (String msg) {
		super(msg);
	}
}
