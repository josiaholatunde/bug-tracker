package com.example.fundamentals.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
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
