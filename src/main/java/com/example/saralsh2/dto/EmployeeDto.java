package com.example.saralsh2.dto;

import com.example.saralsh2.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Long pinfl;
    private LocalDate hireDate;
    private Long organizationId;

    public static EmployeeDto toDto(Employee employee) {
        return getEmployeeDto(employee);
    }

    public static List<EmployeeDto> toDtoList(List<Employee> all) {
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        for (Employee employee : all) {
            EmployeeDto employeeDto = getEmployeeDto(employee);
            employeeDtoList.add(employeeDto);
        }
        return employeeDtoList;
    }

    private static EmployeeDto getEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setPinfl(employee.getPinfl());
        employeeDto.setHireDate(employee.getHireDate());
        employeeDto.setOrganizationId(employee.getOrganization().getId());
        return employeeDto;
    }
}
