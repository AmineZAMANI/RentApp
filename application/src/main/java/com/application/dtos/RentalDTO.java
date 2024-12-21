package com.application.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalDTO {
    private Long id;
    private Long customerId;
    private Long carId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate endDate;
}

