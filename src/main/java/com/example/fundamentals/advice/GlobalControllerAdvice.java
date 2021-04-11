package com.example.fundamentals.advice;

import com.example.fundamentals.dtos.BugTrackerResponse;
import com.example.fundamentals.exceptions.ResourceNotFoundException;
import com.example.fundamentals.utils.ResponseUtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private ResponseUtilService responseUtilService;

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BugTrackerResponse handleResourceNotFoundError(ResourceNotFoundException ex, HttpServletRequest request) {
        return responseUtilService.buildErrorMessage(ex.getMessage());
    }
}
