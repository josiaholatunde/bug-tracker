package com.example.fundamentals.services.impl;

import com.example.fundamentals.dtos.ApplicationDto;
import com.example.fundamentals.models.Application;
import com.example.fundamentals.repositories.ApplicationRepository;
import com.example.fundamentals.services.ApplicationService;
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
    public List<ApplicationDto> retrieveApplications() {
        return StreamSupport.stream(applicationRepository.findAll().spliterator(), false).map(this::convertEntityToData)
                .collect(Collectors.toList());
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
