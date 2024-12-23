package com.domain.repositories;

import java.util.List;


public interface Cars {
    List<com.domain.Car> searchAvailable(String model, Boolean available);
}
