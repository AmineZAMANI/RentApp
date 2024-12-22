package com.infra.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.domain.Car;
import com.domain.Customer;
import com.domain.Rental;
import com.domain.RentalPeriod;
import com.domain.usecases.RentalUseCase;
import com.infra.entity.CarEntity;
import com.infra.entity.CustomerEntity;
import com.infra.entity.RentalEntity;
import com.infra.entity.mappers.CarEntityMapper;
import com.infra.entity.mappers.CustomerEntityMapper;
import com.infra.entity.mappers.RentalEntityMapper;
import com.infra.exceptions.CarAlreadyRentedException;
import com.infra.exceptions.CarNotFoundException;
import com.infra.exceptions.CustomerNotFoundException;
import com.infra.kafka.EventPublisher;
import com.infra.repositories.CarRepository;
import com.infra.repositories.CustomerRepository;
import com.infra.repositories.RentalRepository;

@Service
public class RentalService {
	private final com.domain.usecases.RentalUseCase rentalService = new RentalUseCase();
	private final CarRepository carRepository;
	private final CustomerRepository customerRepository;
	private final RentalRepository rentalRepository;
	private final EventPublisher carRentalProducer;
	private final EventPublisher carReturnProducer;
	private final RentalEntityMapper rentalEntityMapper;
	private final CarEntityMapper carEntityMapper;
	private final CustomerEntityMapper customerEntityMapper;

	public RentalService(CarRepository carRepository, CustomerRepository customerRepository,
			RentalRepository rentalRepository, EventPublisher carRentalProducer, EventPublisher carReturnProducer,
			RentalEntityMapper rentalEntityMapper, CarEntityMapper carEntityMapper,
			CustomerEntityMapper customerEntityMapper) {
		this.carRepository = carRepository;
		this.customerRepository = customerRepository;
		this.rentalRepository = rentalRepository;
		this.carReturnProducer = carReturnProducer;
		this.carRentalProducer = carRentalProducer;
		this.rentalEntityMapper = rentalEntityMapper;
		this.carEntityMapper = carEntityMapper;
		this.customerEntityMapper = customerEntityMapper;
	}

	public Rental rentCar(Long carId, Long customerId, LocalDate startDate, LocalDate endDate) {
		CarEntity car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException("Car not found"));
		CustomerEntity customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

		if (!rentalService.isRentalAllowed(toCar(car), toCustomer(customer), startDate, endDate)) {
			throw new CarAlreadyRentedException("Rental not allowed");
		}

		final Rental rental = Rental
				.builder().car(toCar(car)).customer(toCustomer(customer)).rentalPeriod(RentalPeriod.builder().startDate(startDate)
						.endDate(endDate).durationDays((int) ChronoUnit.DAYS.between(startDate, endDate)).build())
				.build();
		rental.calculateDurationInDays();
		rental.rentCar();
		rentalRepository.save(toRentalEntity(rental));
		/** Publish a message to notify other systems about the car rental */
		this.carRentalProducer.publish("The car " + carId + " was rented by " + customerId);
		return rental;
	}

	public Rental returnCar(Long rentalId) {
		RentalEntity rentatEntity = rentalRepository.findById(rentalId)
				.orElseThrow(() -> new IllegalArgumentException("Rental not found"));
		rentatEntity.returnCar();
		rentalRepository.save(rentatEntity);
		/** Publish a message to notify other systems about the car return change */
		this.carReturnProducer
				.publish("The car " + rentatEntity.getCar().getId() + " was returned by " + rentatEntity.getCustomer().getName());
		return toRental(rentatEntity);
	}

	private RentalEntity toRentalEntity(Rental rental) {
		return this.rentalEntityMapper.toEntity(rental);
	}

	private Rental toRental(RentalEntity entity) {
		return this.rentalEntityMapper.toModel(entity);
	}

	private Car toCar(CarEntity entity) {
		return this.carEntityMapper.toModel(entity);
	}
	
	private Customer toCustomer(CustomerEntity entity) {
		return this.customerEntityMapper.toModel(entity);
	}
}
