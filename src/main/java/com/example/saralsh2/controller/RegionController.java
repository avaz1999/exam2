package com.example.saralsh2.controller;

import com.example.saralsh2.base.ApiResponse;
import com.example.saralsh2.dto.RegionDto;
import com.example.saralsh2.service.RegionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/region")
public class RegionController {
    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @PostMapping
    public ResponseEntity<?> createRegion(@RequestBody RegionDto dto) {
        return ApiResponse.controller(regionService.createRegion(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllRegions() {
        return ApiResponse.controller(regionService.getAllRegion());
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<?> getRegionById(@PathVariable Long id) {
        return ApiResponse.controller(regionService.getRegionById(id));
    }
    @PutMapping
    public ResponseEntity<?> editRegion(@RequestBody RegionDto dto){
        return ApiResponse.controller(regionService.editRegion(dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return  ApiResponse.controller(regionService.delete(id));
    }
}
