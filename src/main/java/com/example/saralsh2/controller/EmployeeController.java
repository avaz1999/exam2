package com.example.saralsh2.controller;

import com.example.saralsh2.base.ApiResponse;
import com.example.saralsh2.dto.EmployeeDto;
import com.example.saralsh2.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody EmployeeDto dto){
        return ApiResponse.controller(employeeService.create(dto));
    }

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody EmployeeDto dto){
        return ApiResponse.controller(employeeService.edit(dto));
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return ApiResponse.controller(employeeService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ApiResponse.controller(employeeService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return ApiResponse.controller(employeeService.delete(id));
    }
}
