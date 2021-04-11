package com.example.fundamentals.controllers;

import com.example.fundamentals.dtos.BugTrackerResponse;
import com.example.fundamentals.services.ApplicationService;
import com.example.fundamentals.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.API_VERSION_1 + "applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping
    public BugTrackerResponse getAllApplications() {
        return applicationService.retrieveApplications();
    }

    @GetMapping("{code}")
    public BugTrackerResponse getApplication(@PathVariable(name = "code") String code) {
        return applicationService.retrieveByCode(code);
    }
}
