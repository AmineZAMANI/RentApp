package com.domain.repositories;

import java.time.LocalDate;
import java.util.List;


public interface Cars {
    List<com.domain.Car> searchAvailable(LocalDate start, LocalDate end,String model);
}
