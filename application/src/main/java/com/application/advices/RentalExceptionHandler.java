package com.application.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.application.exceptions.ErrorResponse;
import com.infra.exceptions.CarAlreadyRentedException;
import com.infra.exceptions.CarNotFoundException;
import com.infra.exceptions.CustomerNotFoundException;

@ControllerAdvice
public class RentalExceptionHandler {

    @ExceptionHandler(CarAlreadyRentedException.class)
    public ResponseEntity<ErrorResponse> handleCarAlreadyRented(CarAlreadyRentedException ex) {
        ErrorResponse errorResponse = new ErrorResponse("CAR_ALREADY_RENTED", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    
    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCarNotFound(CarNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("CAR_NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCarNotFound(CustomerNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("CUSTOMER_NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
