package com.example.saralsh2.entity;

import com.example.saralsh2.base.BaseEntity;
import com.example.saralsh2.enums.CalculationType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "calculation_table")
public class CalculationTable extends BaseEntity {
    private Double amount;
    private Double rate;
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private CalculationType calculationType;
    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Organization organization;

}
