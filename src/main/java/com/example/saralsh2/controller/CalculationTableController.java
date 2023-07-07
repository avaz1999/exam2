package com.example.saralsh2.controller;

import com.example.saralsh2.base.ApiResponse;
import com.example.saralsh2.dto.CalculationTableDto;
import com.example.saralsh2.service.CalculationTableService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/calculate")
public class CalculationTableController {
    private final CalculationTableService calculationTableService;

    public CalculationTableController(CalculationTableService calculationTableService) {
        this.calculationTableService = calculationTableService;
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CalculationTableDto dto){
        return ApiResponse.controller(calculationTableService.create(dto));
    }
    @PutMapping
    public ResponseEntity<?> edit(@RequestBody CalculationTableDto dto){
        return ApiResponse.controller(calculationTableService.edit(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return ApiResponse.controller(calculationTableService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ApiResponse.controller(calculationTableService.getById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return ApiResponse.controller(calculationTableService.delete(id));
    }
    @GetMapping("/task3")
    public ResponseEntity<?> getTask3(@RequestBody String data){
        return ApiResponse.controller(calculationTableService.tsk3(data));
    }
}
