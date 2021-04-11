package com.example.fundamentals.utils;

import com.example.fundamentals.dtos.BugTrackerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResponseUtilService {

    public BugTrackerResponse buildErrorMessage(String message) {

        String errorMessage = Strings.isBlank(message) ? "An error occurred while processing request": message;
        return BugTrackerResponse.builder()
                .requestSuccessful(false)
                .message(errorMessage)
                .build();
    }
}
