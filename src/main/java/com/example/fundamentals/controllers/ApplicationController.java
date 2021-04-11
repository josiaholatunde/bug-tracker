package com.example.fundamentals.controllers;

import com.example.fundamentals.dtos.ApplicationDto;
import com.example.fundamentals.services.ApplicationService;
import com.example.fundamentals.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Constants.API_VERSION_1 + "applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping
    public List<ApplicationDto> getAllApplications() {
        return applicationService.retrieveApplications();
    }
}
