package com.example.saralsh2.service;

import com.example.saralsh2.base.ApiResponse;
import com.example.saralsh2.dto.RegionDto;

public interface RegionService {
    ApiResponse<?> createRegion(RegionDto dto);

    ApiResponse<?> editRegion(RegionDto dto);

    ApiResponse<?> getAllRegion();

    ApiResponse<?> getRegionById(Long id);

    ApiResponse<?> delete(Long id);
}
