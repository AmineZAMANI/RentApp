package com.common.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarDTO {
    private Long id;
    private String model;
    private boolean available;
}

