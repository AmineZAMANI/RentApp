package com.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalPeriod {
	private Long id;

	private LocalDate startDate;

	private LocalDate endDate;

	private Integer durationDays;
	

	public boolean overlapsWith(RentalPeriod other) {
		return (startDate.isBefore(other.endDate) || startDate.equals(other.endDate))
				&& (endDate.isAfter(other.startDate) || endDate.equals(other.startDate));
	}
}