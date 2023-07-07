package com.example.saralsh2.service;

import com.example.saralsh2.base.ApiResponse;
import com.example.saralsh2.dto.OrganizationDto;

public interface OrganizationService {
    ApiResponse<?> createOrganization(OrganizationDto dto);
}
