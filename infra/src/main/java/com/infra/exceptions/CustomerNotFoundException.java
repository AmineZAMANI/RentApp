package com.infra.exceptions;

public class CustomerNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -120075209498852888L;

	public CustomerNotFoundException(String message) {
        super(message);
    }
	
}
