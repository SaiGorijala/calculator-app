package com.example.calculator.repository;

import com.example.calculator.model.Calculation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationRepository extends JpaRepository<Calculation, Long> {
    Page<Calculation> findAllByOrderByTimestampDesc(Pageable pageable);
}