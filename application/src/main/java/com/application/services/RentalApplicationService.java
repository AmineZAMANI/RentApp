package com.application.services;

import org.springframework.stereotype.Service;

import com.domain.Rental;
import com.domain.repositories.Rentals;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentalApplicationService {

    private final Rentals rentals;

    public Rental rentCar(Rental rental) {
       return rentals.rent(rental);
    }

    public Rental returnCar(Rental rental) {
        return rentals.returnCar(rental);
    }
    
    
}
