package com.infra.exceptions;

public class CarAlreadyRentedException extends RuntimeException{

	private static final long serialVersionUID = -959983605689841407L;
	public CarAlreadyRentedException(String message) {
        super(message);
    }
	
}
