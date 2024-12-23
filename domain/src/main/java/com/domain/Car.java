package com.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Car {
	private Long id;
	private String model;
	private boolean available = true;

	public Car(String model) {
		this.model = model;
	}

	public boolean isAvailable() {
		return available;
	}

	public void markAsRented() {
		this.available = false;
	}

	public void markAsAvailable() {
		this.available = true;
	}
}
