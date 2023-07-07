package com.example.saralsh2.repository;

import com.example.saralsh2.entity.CalculationTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalculationTypeRepository extends JpaRepository<CalculationTable, Long> {
    void deleteByEmployeeId(Long id);

    List<CalculationTable> findCalculationTablesByOrganizationId(Long id);
    void deleteByOrganizationId(Long id);
}
