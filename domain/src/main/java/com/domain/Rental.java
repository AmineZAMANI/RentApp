package com.domain;

import java.time.temporal.ChronoUnit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rental {

	private Long id;

	private Car car;

	private Customer customer;

	private RentalPeriod rentalPeriod;

	public void returnCar() {
		this.car.markAsAvailable();
	}
	
	public void rentCar() {
		this.car.markAsRented();
	}


	public void calculateDurationInDays() {
		this.rentalPeriod.setDurationDays((int) ChronoUnit.DAYS.between(this.rentalPeriod.getStartDate(), this.rentalPeriod.getEndDate()));
	}
}
