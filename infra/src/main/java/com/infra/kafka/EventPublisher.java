package com.infra.kafka;

public interface EventPublisher {
    void publish(String event);
}
