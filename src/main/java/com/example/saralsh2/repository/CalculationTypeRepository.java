package com.example.saralsh2.repository;

import com.example.saralsh2.entity.CalculationTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationTypeRepository extends JpaRepository<CalculationTable,Long> {
}
