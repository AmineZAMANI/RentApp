package com.infra.exceptions;

public class RentalNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -120075209498852888L;

	public RentalNotFoundException(String message) {
        super(message);
    }
	
}
