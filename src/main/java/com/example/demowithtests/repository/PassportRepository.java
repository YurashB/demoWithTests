package com.example.demowithtests.repository;

import com.example.demowithtests.domain.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PassportRepository extends JpaRepository<Passport, Integer> {
    @Query(value = "select p from Passport p where p.serialNumber = :serialNumber")
    Optional<Passport> findBySerialNumber(String serialNumber);
}
