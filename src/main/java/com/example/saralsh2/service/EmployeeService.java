package com.example.saralsh2.service;

import com.example.saralsh2.base.ApiResponse;
import com.example.saralsh2.dto.EmployeeDto;

public interface EmployeeService {
    ApiResponse<?> create(EmployeeDto dto);

    ApiResponse<?> edit(EmployeeDto dto);

    ApiResponse<?> getAll();

    ApiResponse<?> getById(Long id);

    ApiResponse<?> delete(Long id);
}
