package com.example.fundamentals.repositories;

import com.example.fundamentals.models.Application;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<Application, Long> {
}
