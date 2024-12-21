package com.application.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CarDTO {
    private Long id;
    private String model;
    private boolean available;
}

