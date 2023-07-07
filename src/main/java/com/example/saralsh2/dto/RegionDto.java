package com.example.saralsh2.dto;

import com.example.saralsh2.entity.Region;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegionDto {
    private Long id;
    private String name;

    public static RegionDto convertToDto(Region region) {
        RegionDto dto = new RegionDto();
        dto.setId(region.getId());
        dto.setName(region.getName());
        return dto;
    }

    public static List<RegionDto> convertToDtoList(List<Region> all) {
        List<RegionDto> regionDtoList = new ArrayList<>();
        for (Region region : all) {
            RegionDto regionDto = new RegionDto();
            regionDto.setId(region.getId());
            regionDto.setName(region.getName());
            regionDtoList.add(regionDto);
        }
        return regionDtoList;
    }
}
