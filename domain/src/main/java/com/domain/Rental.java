package com.domain;

import java.time.temporal.ChronoUnit;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Rental {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "car_id", nullable = false)
	private Car car;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rental_period_id", nullable = false)
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
