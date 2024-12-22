package com.infra.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
public class CarEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String model;
	private boolean available = true;

	public CarEntity(String model) {
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
