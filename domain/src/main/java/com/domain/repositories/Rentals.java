package com.domain.repositories;

public interface Rentals {
    com.domain.Rental rent(com.domain.Rental rental);
    com.domain.Rental returnCar(com.domain.Rental rental);
}
