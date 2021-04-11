package com.example.fundamentals.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDto {

    private String code;

    private String name;

    private String description;

    private String owner;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
