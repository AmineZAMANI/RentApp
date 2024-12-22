package com.infra.adapters;

import org.springframework.stereotype.Repository;

import com.domain.Car;
import com.domain.Customer;
import com.domain.Rental;
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
import com.infra.kafka.EventPublisher;
import com.infra.repositories.CarRepository;
import com.infra.repositories.CustomerRepository;
import com.infra.repositories.RentalRepository;

@Repository
public class RentalsImpl implements Rentals {

	private final com.domain.usecases.RentalUseCase rentalService = new RentalUseCase();
	private final CarRepository carRepository;
	private final CustomerRepository customerRepository;
	private final RentalRepository rentalRepository;
	private final EventPublisher carRentalProducer;
	private final EventPublisher carReturnProducer;
	private final RentalEntityMapper rentalEntityMapper;
	private final CarEntityMapper carEntityMapper;
	private final CustomerEntityMapper customerEntityMapper;

	public RentalsImpl(CarRepository carRepository, CustomerRepository customerRepository,
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

	@Override
	public Rental rent(Rental rental) {

		CarEntity carEntity = carRepository.findById(rental.getCar().getId())
				.orElseThrow(() -> new CarNotFoundException("Car not found"));
		CustomerEntity customerEntity = customerRepository.findById(rental.getCustomer().getId())
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
		
		Car car = toCar(carEntity);
		Customer customer = toCustomer(customerEntity);
		
		if (!rentalService.isRentalAllowed(car, customer, rental.getRentalPeriod().getStartDate(),
				rental.getRentalPeriod().getEndDate())) {
			throw new CarAlreadyRentedException("Rental not allowed");
		}
		rental.setCar(car);
		rental.calculateDurationInDays();
		rental.rentCar();
		RentalEntity persistentRental = rentalRepository.save(toRentalEntity(rental));
		carRepository.save(toCarEntity(car));
		/** Publish a message to notify other systems about the car rental */
		this.carRentalProducer
				.publish("The car " + rental.getCar().getModel() + " was rented by " + rental.getCustomer().getName());
		return toRental(persistentRental);
	}

	@Override
	public Rental returnCar(Rental rental) {
		
		RentalEntity rentatEntity = rentalRepository.findById(rental.getId())
				.orElseThrow(() -> new IllegalArgumentException("Rental not found"));
		
		Rental rentalDomain = toRental(rentatEntity);
		rentalDomain.returnCar();
		RentalEntity entity = toRentalEntity(rentalDomain);
		rentalRepository.save(entity);
		carRepository.save(entity.getCar());
		/** Publish a message to notify other systems about the car return change */
		this.carReturnProducer.publish("The car " + rentatEntity.getCar().getId() + " was returned by "
				+ rentatEntity.getCustomer().getName());
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
