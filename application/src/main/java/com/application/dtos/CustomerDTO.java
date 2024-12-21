package com.application.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
}

