package com.infra.exceptions;

public class CarNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -120075209498852888L;

	public CarNotFoundException(String message) {
        super(message);
    }
	
}
