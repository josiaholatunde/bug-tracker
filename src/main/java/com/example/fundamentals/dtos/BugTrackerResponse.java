package com.example.fundamentals.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BugTrackerResponse {
    private boolean requestSuccessful;
    private Object data;
    private String message;
}
