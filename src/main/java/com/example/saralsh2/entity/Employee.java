package com.example.saralsh2.entity;

import com.example.saralsh2.base.BaseEntity;
import com.example.saralsh2.dto.EmployeeDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "employee")
public class Employee extends BaseEntity {
    private String firstName;
    private String lastName;
    private Long pinfl;
    private LocalDate hireDate;
    @ManyToOne
    private Organization organization;

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private List<CalculationTable> calculationTables;

    public static Employee toEntity(EmployeeDto dto, Organization organization) {
        Employee employee = new Employee();
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setPinfl(dto.getPinfl());
        employee.setHireDate(dto.getHireDate());
        employee.setOrganization(organization);
        return employee;
    }

    public static Employee toEditEntity(Employee employee, Organization organization,EmployeeDto dto) {
        employee.setFirstName(dto.getFirstName() != null ? dto.getFirstName() : employee.getFirstName());
        employee.setLastName(dto.getLastName() != null ? dto.getLastName() : employee.getLastName());
        employee.setPinfl(dto.getPinfl() != null ? dto.getPinfl() : employee.getPinfl());
        employee.setHireDate(dto.getHireDate() != null ? dto.getHireDate() : employee.getHireDate());
        employee.setOrganization(organization != null ? organization : employee.getOrganization());
        return employee;
    }
}
