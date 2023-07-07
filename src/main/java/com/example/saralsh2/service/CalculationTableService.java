package com.example.saralsh2.service;

import com.example.saralsh2.base.ApiResponse;
import com.example.saralsh2.dto.CalculationTableDto;

public interface CalculationTableService {
    ApiResponse<?> create(CalculationTableDto dto);

    ApiResponse<?> edit(CalculationTableDto dto);

    ApiResponse<?> getAll();

    ApiResponse<?> getById(Long id);

    ApiResponse<?> delete(Long id);
    ApiResponse<?> tsk3();
}
