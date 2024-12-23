package com.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CarRentedEvent {
    private final String rentalId;
    private final String carId;
    private final String customerId;
    private final String rentalStartDate;
    private final String rentalEndDate;
}
