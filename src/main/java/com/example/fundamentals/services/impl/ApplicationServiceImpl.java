package com.example.fundamentals.services.impl;

import com.example.fundamentals.dtos.ApplicationDto;
import com.example.fundamentals.dtos.BugTrackerResponse;
import com.example.fundamentals.exceptions.ResourceNotFoundException;
import com.example.fundamentals.models.Application;
import com.example.fundamentals.repositories.ApplicationRepository;
import com.example.fundamentals.services.ApplicationService;
import com.example.fundamentals.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private static final Long MAX_GENERATION_ATTEMPTS = 99999999l;
    @Override
    public BugTrackerResponse retrieveApplications() {
        List<ApplicationDto> applicationDtoList = StreamSupport.stream(applicationRepository.findAll().spliterator(), false).map(this::convertEntityToData)
                .collect(Collectors.toList());
        return BugTrackerResponse
                .builder()
                .requestSuccessful(true)
                .data(applicationDtoList)
                .message(Constants.SUCCESS_RESPONSE_MESSAGE)
                .build();
    }

    @Override
    public BugTrackerResponse retrieveByCode(String code) {
        ApplicationDto applicationDto = findByCode(code).orElseThrow(() ->
                new ResourceNotFoundException("Application was not found. Kindly try again with a valid code"));

        return BugTrackerResponse
                .builder()
                .requestSuccessful(true)
                .data(applicationDto)
                .message(Constants.SUCCESS_RESPONSE_MESSAGE)
                .build();
    }

    public Optional<ApplicationDto> findByCode(String code) {
        Optional<Application> applicationOptional = applicationRepository.findByCode(code);
        return applicationOptional.isPresent() ? Optional.of(convertEntityToData(applicationOptional.get())): Optional.empty();
    }

    private String generateCode() throws Exception {
        String generatedCode = "";
        long generationAttenpts = 0;
        while (generationAttenpts >= MAX_GENERATION_ATTEMPTS) {
            generatedCode = UUID.randomUUID().toString();
            if (!findByCode(generatedCode).isPresent()) return  generatedCode;
            generationAttenpts++;
        }
        throw new Exception("Unable to generate code for entity application");
    }

    private ApplicationDto convertEntityToData(Application application) {
        ApplicationDto applicationDto = ApplicationDto.builder()
                .build();
        BeanUtils.copyProperties(application, applicationDto);
        return applicationDto;
    }
}
