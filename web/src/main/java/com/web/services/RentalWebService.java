package com.web.services;

import org.springframework.stereotype.Service;

import com.application.services.RentalApplicationService;
import com.common.dtos.RentalDTO;
import com.common.mappers.RentalMapper;
import com.domain.Rental;

@Service
public class RentalWebService {

	private final RentalMapper rentalMapper;
	private final RentalApplicationService rentalApplicationService;

	public RentalWebService(RentalMapper rentalMapper, RentalApplicationService rentalApplicationService) {
		this.rentalMapper = rentalMapper;
		this.rentalApplicationService = rentalApplicationService;
	}

	public RentalDTO rentCar(RentalDTO rentalDTO) {
		final Rental rental = rentalApplicationService.rentCar(rentalMapper.toDomain(rentalDTO));
		return rentalMapper.toDTO(rental);
	}

	public RentalDTO returnCar(Long rentalId) {
		final Rental rental = rentalApplicationService.returnCar(Rental.builder().id(rentalId).build());
		return rentalMapper.toDTO(rental);
	}

}
