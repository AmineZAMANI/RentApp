package com.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class RentalPeriod {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDate startDate;

	@Column(nullable = false)
	private LocalDate endDate;

	@Column(nullable = false)
	private Integer durationDays;
	

	public boolean overlapsWith(RentalPeriod other) {
		return (startDate.isBefore(other.endDate) || startDate.equals(other.endDate))
				&& (endDate.isAfter(other.startDate) || endDate.equals(other.startDate));
	}
}