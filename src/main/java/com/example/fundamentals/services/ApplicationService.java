package com.example.fundamentals.services;

import com.example.fundamentals.dtos.BugTrackerResponse;

public interface ApplicationService {

    BugTrackerResponse retrieveApplications();

    BugTrackerResponse retrieveByCode(String code);
}
