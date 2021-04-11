package com.example.fundamentals.seeders;

import com.example.fundamentals.models.Application;
import com.example.fundamentals.repositories.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationSeeder implements ApplicationListener<ApplicationReadyEvent>{

    private final ApplicationRepository applicationRepository;

    public void onApplicationEvent(final ApplicationReadyEvent applicationReadyEvent) {

        log.info("Application seeder is ready to start");

        try {
            List applicationsToBeSeeded = Arrays.asList(
                    Application.builder().name("TrackZilla").description("Application for tracking bugs")
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .build(),
                    Application.builder().name("Expenses").description("Application for tracking expenses")
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .build(),
                    Application.builder().name("Notifications").description("Application for tracking notifications sent")
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .build()
            );
            applicationRepository.saveAll(applicationsToBeSeeded);

            log.info("Application seeder has finished executing");
        } catch (Exception ex) {
            log.error("An error occurred while running application seeder {}", ex.getMessage());
        }

    }
}