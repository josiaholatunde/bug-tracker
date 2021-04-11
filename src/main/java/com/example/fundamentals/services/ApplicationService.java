package com.example.fundamentals.services;

import com.example.fundamentals.dtos.ApplicationDto;

import java.util.List;

public interface ApplicationService {
    List<ApplicationDto> retrieveApplications();
}
