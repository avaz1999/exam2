package com.example.saralsh2.entity;

import com.example.saralsh2.base.BaseEntity;
import com.example.saralsh2.dto.CalculationTableDto;
import com.example.saralsh2.enums.CalculationType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "calculation_table")
public class CalculationTable extends BaseEntity {
    private Double amount;
    private Double rate;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private CalculationType calculationType;
    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Organization organization;

    public static CalculationTable toEntity(CalculationTableDto dto, Employee employee, Organization organization) {
        CalculationTable calculationTable = new CalculationTable();
        calculationTable.setAmount(dto.getAmount());
        calculationTable.setDate(dto.getDate());
        calculationTable.setRate(dto.getRate());
        calculationTable.setCalculationType(dto.getCalculationType());
        calculationTable.setOrganization(organization);
        calculationTable.setEmployee(employee);
        return calculationTable;
    }

    public static CalculationTable toUpdateEntity(CalculationTable calculationTable,CalculationTableDto dto, Organization organization, Employee employee) {
        calculationTable.setAmount(dto.getAmount() != null ? dto.getAmount() : calculationTable.getAmount());
        calculationTable.setDate(dto.getDate() != null ? dto.getDate() : calculationTable.getDate());
        calculationTable.setRate(dto.getRate() != null ? dto.getRate() : calculationTable.getRate());
        calculationTable.setCalculationType(dto.getCalculationType() != null ? dto.getCalculationType() : calculationTable.getCalculationType());
        calculationTable.setOrganization(organization != null ? organization : calculationTable.getOrganization());
        calculationTable.setEmployee(employee != null ? employee : calculationTable.getEmployee());
        return calculationTable;
    }
}
