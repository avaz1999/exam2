package com.example.saralsh2.dto;

import com.example.saralsh2.entity.Organization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrganizationDto {
    private Long id;
    private String name;
    private Long regionId;

    public static List<OrganizationDto> toDtoList(List<Organization> all) {
        List<OrganizationDto> organizationDtoList = new ArrayList<>();
        for (Organization organization : all) {
            OrganizationDto organizationDto = new OrganizationDto();
            organizationDto.setId(organization.getId());
            organizationDto.setName(organization.getName());
            organizationDto.setRegionId(organization.getRegion().getId());
            organizationDtoList.add(organizationDto);
        }
        return organizationDtoList;
    }

    public static OrganizationDto toDto(Organization organization) {
        return new OrganizationDto(organization.getId(), organization.getName(), organization.getRegion().getId());
    }
}
