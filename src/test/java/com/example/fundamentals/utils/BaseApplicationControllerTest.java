package com.example.fundamentals.utils;

import com.example.fundamentals.dtos.ApplicationDto;
import com.example.fundamentals.integrations.BaseIntegrationTest;
import com.example.fundamentals.models.Application;
import com.example.fundamentals.repositories.ApplicationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.UUID;

public class BaseApplicationControllerTest extends BaseIntegrationTest {

    @Autowired
    private ApplicationRepository applicationRepository;

    public ApplicationDto buildApplicationDtoFromApplication(Application application) {
        ApplicationDto applicationDto = ApplicationDto.builder().build();
        BeanUtils.copyProperties(application, applicationDto);
        return applicationDto;
    }

    public Application seedTestApplication() {
        Application application = Application.builder().name("TrackZilla").description("Application for tracking bugs")
                .code(UUID.randomUUID().toString())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return applicationRepository.save(application);
    }
}
