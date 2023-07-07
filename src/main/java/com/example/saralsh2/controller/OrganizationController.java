package com.example.saralsh2.controller;

import com.example.saralsh2.base.ApiResponse;
import com.example.saralsh2.dto.OrganizationDto;
import com.example.saralsh2.service.OrganizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {
    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrganizationDto dto) {
        return ApiResponse.controller(organizationService.createOrganization(dto));
    }

    @PutMapping
    ResponseEntity<?> edit(@RequestBody OrganizationDto dto) {
        return ApiResponse.controller(organizationService.editOrganization(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ApiResponse.controller(organizationService.getAllOrganizations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ApiResponse.controller(organizationService.getByIdOrganization(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ApiResponse.controller(organizationService.deleteOrganization(id));
    }
}
