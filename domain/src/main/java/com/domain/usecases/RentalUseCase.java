package com.domain.usecases;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.domain.Car;
import com.domain.Customer;

@Service
public class RentalUseCase {
    public boolean isRentalAllowed(Car car, Customer customer, LocalDate startDate, LocalDate endDate) {
        return car.isAvailable();
    }
}
