package com.application.services;

import org.springframework.stereotype.Service;

import com.application.dtos.RentalDTO;
import com.application.mappers.RentalMapper;
import com.domain.Rental;
import com.infra.repositories.CustomerRepository;
import com.infra.services.RentalService;

@Service
public class RentalApplicationService {

    private final RentalService rentalService;
    private final RentalMapper rentalMapper;
    

    public RentalApplicationService(RentalService rentalService, CustomerRepository customerRepository, RentalMapper rentalMapper) {
        this.rentalService = rentalService;
        this.rentalMapper = rentalMapper;
    }

    // Rent a car
    public RentalDTO rentCar(RentalDTO rentalDTO) {
        Rental rental = rentalService.rentCar(rentalDTO.getCarId(), rentalDTO.getCustomerId(), rentalDTO.getStartDate(), rentalDTO.getEndDate());
        return this.rentalMapper.toDTO(rental);
    }

    // Return a car
    public RentalDTO returnCar(Long rentalId) {
        Rental rental = rentalService.returnCar(rentalId);
        return this.rentalMapper.toDTO(rental);
    }
}
