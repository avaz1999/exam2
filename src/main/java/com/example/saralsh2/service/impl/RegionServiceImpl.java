package com.example.saralsh2.service.impl;

import com.example.saralsh2.base.ApiResponse;
import com.example.saralsh2.dto.RegionDto;
import com.example.saralsh2.entity.Organization;
import com.example.saralsh2.entity.Region;
import com.example.saralsh2.repository.OrganizationRepository;
import com.example.saralsh2.repository.RegionRepository;
import com.example.saralsh2.service.RegionService;
import com.example.saralsh2.utils.ResponseMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceImpl implements RegionService {
    public final RegionRepository regionRepository;
    private final OrganizationRepository organizationRepository;

    public RegionServiceImpl(RegionRepository repository, OrganizationRepository organizationRepository) {
        this.regionRepository = repository;
        this.organizationRepository = organizationRepository;
    }

    @Override
    public ApiResponse<?> createRegion(RegionDto dto) {
        try {
            String regionName = dto.getName();
            if (regionName == null) {
                return new ApiResponse<>(false, ResponseMessage.OBJECT_IS_NULL);
            }
            Region region = new Region();
            region.setName(regionName);
            Region saveRegion = regionRepository.save(region);
            RegionDto regionDto = RegionDto.convertToDto(saveRegion);
            return new ApiResponse<>(true, ResponseMessage.SUCCESS,regionDto);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ApiResponse<>(false, ResponseMessage.SERVER_ERROR);
        }
    }

    @Override
    public ApiResponse<?> editRegion(RegionDto dto) {
        try {
            if (dto.getId() == null) {
                return new ApiResponse<>(false, ResponseMessage.OBJECT_IS_NULL);
            }
            Optional<Region> regionById = regionRepository.findById(dto.getId());
            if (regionById.isEmpty()) {
                return new ApiResponse<>(false, ResponseMessage.OBJECT_NOT_FOUND);
            }
            Region region = regionById.get();
            Optional<Organization> organizationById = organizationRepository.findByRegionId(dto.getId());
            if (organizationById.isPresent()) {
                Organization organization = organizationById.get();
                region.setName(dto.getName());
                organization.setRegion(region);
                regionRepository.save(region);
                organizationRepository.save(organization);
            }
            else {
                region.setName(dto.getName());
                regionRepository.save(region);
            }
            return new ApiResponse<>(true, ResponseMessage.SUCCESS);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, ResponseMessage.SERVER_ERROR);
    }

    @Override
    public ApiResponse<?> getAllRegion() {
        try {
            List<RegionDto> regionDtoList = RegionDto.convertToDtoList(regionRepository.findAll());
            return new ApiResponse<>(true, ResponseMessage.SUCCESS, regionDtoList);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, ResponseMessage.SERVER_ERROR);
    }


    @Override
    public ApiResponse<?> getRegionById(Long id) {
        try {
            if (id == null) {
                return new ApiResponse<>(false, ResponseMessage.OBJECT_IS_NULL);
            }
            Optional<Region> regionById = regionRepository.findById(id);
            if (regionById.isEmpty()) {
                return new ApiResponse<>(false, ResponseMessage.OBJECT_NOT_FOUND);
            }
            Region region = regionById.get();
            RegionDto regionDto = RegionDto.convertToDto(region);
            return new ApiResponse<>(true, ResponseMessage.SUCCESS, regionDto);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, ResponseMessage.SERVER_ERROR);
    }

    @Override
    public ApiResponse<?> delete(Long regionId) {
        try {
            if (regionId == null) {
                return new ApiResponse<>(false, ResponseMessage.OBJECT_IS_NULL);
            }
            Optional<Region> regionById = regionRepository.findById(regionId);
            if (regionById.isEmpty()) {
                return new ApiResponse<>(false, ResponseMessage.OBJECT_NOT_FOUND);
            }
            regionRepository.deleteById(regionId);
            organizationRepository.deleteByRegionId(regionId);
            return new ApiResponse<>(true, ResponseMessage.DELETE);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, ResponseMessage.SERVER_ERROR);
    }
}
