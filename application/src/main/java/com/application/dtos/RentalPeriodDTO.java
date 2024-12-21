package com.application.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RentalPeriodDTO {
    private Long id;
    private int durationDay;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDate endDate;
}
