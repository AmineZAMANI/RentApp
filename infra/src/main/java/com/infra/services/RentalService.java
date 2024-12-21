package com.infra.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.domain.Car;
import com.domain.Customer;
import com.domain.Rental;
import com.domain.RentalPeriod;
import com.infra.exceptions.CarAlreadyRentedException;
import com.infra.exceptions.CarNotFoundException;
import com.infra.exceptions.CustomerNotFoundException;
import com.infra.repositories.CarRepository;
import com.infra.repositories.CustomerRepository;
import com.infra.repositories.RentalRepository;

@Service
public class RentalService {
	private final com.domain.services.RentalUseCase rentalService;
	private final CarRepository carRepository;
	private final CustomerRepository customerRepository;
	private final RentalRepository rentalRepository;

	public RentalService(com.domain.services.RentalUseCase rentalService, CarRepository carRepository,
			CustomerRepository customerRepository, RentalRepository rentalRepository) {
		this.rentalService = rentalService;
		this.carRepository = carRepository;
		this.customerRepository = customerRepository;
		this.rentalRepository = rentalRepository;
	}

	public Rental rentCar(Long carId, Long customerId, LocalDate startDate, LocalDate endDate) {
		Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException("Car not found"));
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

		if (!rentalService.isRentalAllowed(car, customer, startDate, endDate)) {
			throw new CarAlreadyRentedException("Rental not allowed");
		}

		Rental rental = Rental
				.builder().car(car).customer(customer).rentalPeriod(RentalPeriod.builder().startDate(startDate)
						.endDate(endDate).durationDays((int) ChronoUnit.DAYS.between(startDate, endDate)).build())
				.build();
		rental.calculateDurationInDays();
		rental.rentCar();
		rentalRepository.save(rental);
		return rental;
	}

	public Rental returnCar(Long rentalId) {
		Rental rental = rentalRepository.findById(rentalId)
				.orElseThrow(() -> new IllegalArgumentException("Rental not found"));
		rental.returnCar();
		rentalRepository.save(rental);
		return rental;
	}
}
