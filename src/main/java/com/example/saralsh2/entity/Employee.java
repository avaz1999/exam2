package com.example.saralsh2.entity;

import com.example.saralsh2.base.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "employee")
public class Employee extends BaseEntity {
    private String firstName;
    private String lastName;
    private Long pinfl;
    private LocalDate hireDate;
    @ManyToOne
    private Organization organization;

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private List<CalculationTable> calculationTables;

}
