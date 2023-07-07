package com.example.saralsh2.dto;

import com.example.saralsh2.entity.CalculationTable;
import com.example.saralsh2.enums.CalculationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CalculationTableDto {
    private Long id;
    private Long employeeId;
    private Double amount;
    private Double rate;
    private LocalDate date;
    private Long organizationId;
    private CalculationType calculationType;

    public static CalculationTableDto toDto(CalculationTable entity) {
        CalculationTableDto calculationTableDto = new CalculationTableDto();
        calculationTableDto.setId(entity.getId());
        calculationTableDto.setAmount(entity.getAmount());
        calculationTableDto.setDate(entity.getDate());
        calculationTableDto.setRate(entity.getRate());
        calculationTableDto.setCalculationType(entity.getCalculationType());
        calculationTableDto.setOrganizationId(entity.getOrganization().getId());
        calculationTableDto.setEmployeeId(entity.getEmployee().getId());
        return calculationTableDto;
    }

    public static List<CalculationTableDto> toDtoList(List<CalculationTable> all) {
        List<CalculationTableDto> calculationTableDtoList = new ArrayList<>();
        for (CalculationTable calculationTable : all) {
            CalculationTableDto calculationTableDto = new CalculationTableDto();
            calculationTableDto.setId(calculationTable.getId());
            calculationTableDto.setAmount(calculationTable.getAmount());
            calculationTableDto.setRate(calculationTable.getRate());
            calculationTableDto.setDate(calculationTable.getDate());
            calculationTableDto.setOrganizationId(calculationTable.getOrganization().getId());
            calculationTableDto.setEmployeeId(calculationTable.getEmployee().getId());
            calculationTableDto.setCalculationType(calculationTable.getCalculationType());
            calculationTableDtoList.add(calculationTableDto);
        }
        return calculationTableDtoList;
    }
}
