package com.example.saralsh2.repository;

import com.example.saralsh2.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findEmployeesByOrganizationId(Long id);
    void deleteAllByOrganizationId(Long orgId);
}
