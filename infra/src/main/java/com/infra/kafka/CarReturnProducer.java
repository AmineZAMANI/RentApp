package com.infra.kafka;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Qualifier("carReturnProducer")
public class CarReturnProducer implements EventPublisher{

    private final KafkaTemplate<String, String> kafkaTemplate;

    public CarReturnProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(String event) {
        kafkaTemplate.send("car-return-topic", event);
    }
}
