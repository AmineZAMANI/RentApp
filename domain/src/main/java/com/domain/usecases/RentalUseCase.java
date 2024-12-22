package com.domain.usecases;

import java.time.LocalDate;

import com.domain.Car;
import com.domain.Customer;

public class RentalUseCase {
    public boolean isRentalAllowed(Car car, Customer customer, LocalDate startDate, LocalDate endDate) {
        return car.isAvailable();
    }
}
