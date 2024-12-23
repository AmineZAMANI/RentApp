package com.infra.kafka;

import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.domain.event.CarRentedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CarRentedEventListener {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC_NAME = "rentals_topic";

    @EventListener
    public void handleCarRentedEvent(CarRentedEvent event) {
        log.info("Handling CarRentedEvent: {}", event);

        String message = CarRentedKafkaMessage.builder()
                .rentalId(event.getRentalId())
                .carId(event.getCarId())
                .customerId(event.getCustomerId())
                .rentalStartDate(event.getRentalStartDate())
                .rentalEndDate(event.getRentalEndDate())
                .build()
                .toJson();

        kafkaTemplate.send(TOPIC_NAME, message)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Failed to send message: {}", message, ex);
                    } else {
                        log.info("Message sent successfully: {}", message);
                    }
                });
    }

}
