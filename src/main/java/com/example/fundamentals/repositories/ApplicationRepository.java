package com.example.fundamentals.repositories;

import com.example.fundamentals.models.Application;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ApplicationRepository extends CrudRepository<Application, Long> {

    Optional<Application> findByCode(String code);
}
