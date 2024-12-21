package com.infra.kafka;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Qualifier("carRentalProducer")
public class CarRentalProducer implements EventPublisher{

    private final KafkaTemplate<String, String> kafkaTemplate;

    public CarRentalProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(String event) {
        kafkaTemplate.send("car-rental-topic", event);
    }
}
