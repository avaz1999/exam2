package com.example.saralsh2.entity;

import com.example.saralsh2.base.BaseEntity;
import com.example.saralsh2.dto.OrganizationDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "organizations")
public class Organization extends BaseEntity {
    private String name;

    @ManyToOne
    private Region region;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Employee> employees;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<CalculationTable> calculationTables;

    public static Organization toEntity(OrganizationDto dto, Region region) {
        Organization organization = new Organization();
        organization.setName(dto.getName());
        organization.setRegion(region);
        return organization;
    }
}
