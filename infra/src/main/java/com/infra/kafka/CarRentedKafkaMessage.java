package com.infra.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CarRentedKafkaMessage {
    private final String rentalId;
    private final String carId;
    private final String customerId;
    private final String rentalStartDate;
    private final String rentalEndDate;

    public String toJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing Kafka message to JSON", e);
        }
    }
}
