package com.shirashop.pedidoapi.exceptions;

public class EmptyListException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public EmptyListException(String msg) {
		super(msg);
	}
}
