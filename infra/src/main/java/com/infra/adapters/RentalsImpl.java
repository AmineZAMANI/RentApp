package com.infra.adapters;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import com.domain.Car;
import com.domain.Customer;
import com.domain.Rental;
import com.domain.event.CarRentedEvent;
import com.domain.repositories.Rentals;
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
import com.infra.exceptions.RentalNotFoundException;
import com.infra.repositories.CarRepository;
import com.infra.repositories.CustomerRepository;
import com.infra.repositories.RentalRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@AllArgsConstructor
public class RentalsImpl implements Rentals {

	private final com.domain.usecases.RentalUseCase rentalService = new RentalUseCase();
	private final CarRepository carRepository;
	private final CustomerRepository customerRepository;
	private final RentalRepository rentalRepository;
	private final RentalEntityMapper rentalEntityMapper;
	private final CarEntityMapper carEntityMapper;
	private final CustomerEntityMapper customerEntityMapper;

	private final ApplicationEventPublisher eventPublisher;

	@Override
	public Rental rent(Rental rental) {

		CarEntity carEntity = carRepository.findById(rental.getCar().getId())
				.orElseThrow(() -> new CarNotFoundException("Car not found"));
		CustomerEntity customerEntity = customerRepository.findById(rental.getCustomer().getId())
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

		Car car = toCar(carEntity);
		Customer customer = toCustomer(customerEntity);
		
		checkAvailability(rental, car, customer);
		
		RentalEntity persistentRental = persistRental(rental, car);
		
		log.info("Car successfully rented with rental ID: {}", persistentRental .getId());
		CarRentedEvent event = CarRentedEvent.builder().rentalId(persistentRental .getId().toString())
				.carId(persistentRental .getCar().getId().toString()).customerId(persistentRental .getCustomer().getId().toString())
				.rentalStartDate(persistentRental .getRentalPeriod().getStartDate().toString())
				.rentalEndDate(persistentRental .getRentalPeriod().getEndDate().toString()).build();
		eventPublisher.publishEvent(event);

		return toRental(persistentRental);
	}

	private RentalEntity persistRental(Rental rental, Car car) {
		rental.setCar(car);
		rental.calculateDurationInDays();
		rental.rentCar();
		RentalEntity persistentRental = rentalRepository.save(toRentalEntity(rental));
		carRepository.saveAndFlush(toCarEntity(car));
		return persistentRental;
	}

	private void checkAvailability(Rental rental, Car car, Customer customer) {
		if (!rentalService.isRentalAllowed(car, customer, rental.getRentalPeriod().getStartDate(),
				rental.getRentalPeriod().getEndDate())) {
			throw new CarAlreadyRentedException("Rental not allowed");
		}
	}

	@Override
	public Rental returnCar(Rental rental) {

		RentalEntity rentatEntity = rentalRepository.findById(rental.getId())
				.orElseThrow(() -> new RentalNotFoundException("Rental not found"));

		Rental rentalDomain = toRental(rentatEntity);
		rentalDomain.returnCar();
		RentalEntity entity = toRentalEntity(rentalDomain);
		rentalRepository.save(entity);
		carRepository.save(entity.getCar());
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

	private CarEntity toCarEntity(Car car) {
		return this.carEntityMapper.toEntity(car);
	}

	private Customer toCustomer(CustomerEntity entity) {
		return this.customerEntityMapper.toModel(entity);
	}

}
