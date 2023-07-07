package com.example.saralsh2.service.impl;

import com.example.saralsh2.base.ApiResponse;
import com.example.saralsh2.dto.OrganizationDto;
import com.example.saralsh2.entity.CalculationTable;
import com.example.saralsh2.entity.Employee;
import com.example.saralsh2.entity.Organization;
import com.example.saralsh2.entity.Region;
import com.example.saralsh2.repository.CalculationTypeRepository;
import com.example.saralsh2.repository.EmployeeRepository;
import com.example.saralsh2.repository.OrganizationRepository;
import com.example.saralsh2.repository.RegionRepository;
import com.example.saralsh2.service.OrganizationService;
import com.example.saralsh2.utils.ResponseMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final RegionRepository regionRepository;
    private final EmployeeRepository employeeRepository;
    private final CalculationTypeRepository calculationTypeRepository;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository, RegionRepository regionRepository, EmployeeRepository employeeRepository, CalculationTypeRepository calculationTypeRepository) {
        this.organizationRepository = organizationRepository;
        this.regionRepository = regionRepository;
        this.employeeRepository = employeeRepository;
        this.calculationTypeRepository = calculationTypeRepository;
    }

    @Override
    public ApiResponse<?> createOrganization(OrganizationDto dto) {
        try {
            if (dto.getRegionId() == null) return new ApiResponse<>(false, ResponseMessage.OBJECT_IS_NULL);

            if (dto.getName() == null) return new ApiResponse<>(false, ResponseMessage.OBJECT_IS_NULL);

            Optional<Region> byId = regionRepository.findById(dto.getRegionId());
            if (byId.isEmpty()) return new ApiResponse<>(false, ResponseMessage.REGION_NOT_FOUND);

            Region region = byId.get();
            Organization organization = Organization.toEntity(dto, region);
            organizationRepository.save(organization);
            OrganizationDto organizationDto = OrganizationDto.toDto(organization);
            return new ApiResponse<>(true, ResponseMessage.SUCCESS, organizationDto);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, ResponseMessage.SERVER_ERROR);
    }

    @Override
    public ApiResponse<?> editOrganization(OrganizationDto dto) {
        try {
            if (dto.getId() == null)
                return new ApiResponse<>(false, ResponseMessage.OBJECT_IS_NULL);

            Optional<Region> byId = regionRepository.findById(dto.getRegionId());
            if (byId.isEmpty())
                return new ApiResponse<>(false, ResponseMessage.REGION_NOT_FOUND);

            Region region = byId.get();
            Optional<Organization> organizationById = organizationRepository.findById(dto.getId());
            if (organizationById.isEmpty())
                return new ApiResponse<>(false, ResponseMessage.OBJECT_NOT_FOUND);

            Organization organization = organizationById.get();
            organization.setName(dto.getName());
            organization.setRegion(region);
            Organization save = organizationRepository.save(organization);
            OrganizationDto organizationDto = OrganizationDto.toDto(save);
            return new ApiResponse<>(true, ResponseMessage.SUCCESS, organizationDto);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, ResponseMessage.SERVER_ERROR);
    }

    @Override
    public ApiResponse<?> getAllOrganizations() {
        try {
            List<Organization> all = organizationRepository.findAll();
            List<OrganizationDto> organizationDtoList = OrganizationDto.toDtoList(all);
            return new ApiResponse<>(true, ResponseMessage.SUCCESS, organizationDtoList);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, ResponseMessage.SERVER_ERROR);
    }

    @Override
    public ApiResponse<?> getByIdOrganization(Long id) {
        try {
            if (id == null) return new ApiResponse<>(false, ResponseMessage.OBJECT_IS_NULL);
            Optional<Organization> byId = organizationRepository.findById(id);
            if (byId.isEmpty()) return new ApiResponse<>(false, ResponseMessage.OBJECT_NOT_FOUND);
            Organization organization = byId.get();
            OrganizationDto dto = OrganizationDto.toDto(organization);
            return new ApiResponse<>(false, ResponseMessage.SUCCESS, dto);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, ResponseMessage.SERVER_ERROR);
    }

    @Override
    public ApiResponse<?> deleteOrganization(Long id) {
        try {
            if (id == null) return new ApiResponse<>(false, ResponseMessage.OBJECT_IS_NULL);
            List<Employee> employeeByOrganizationId = employeeRepository.findEmployeesByOrganizationId(id);
            List<CalculationTable> calculationTablesByOrganizationId = calculationTypeRepository.findCalculationTablesByOrganizationId(id);
            if (employeeByOrganizationId.isEmpty() || calculationTablesByOrganizationId.isEmpty()){
                    organizationRepository.deleteById(id);
                    return new ApiResponse<>(true, ResponseMessage.DELETE);
            }
            calculationTypeRepository.deleteAll(calculationTablesByOrganizationId);
            employeeRepository.deleteAll(employeeByOrganizationId);
            organizationRepository.deleteById(id);
            return new ApiResponse<>(true, ResponseMessage.DELETE);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ApiResponse<>(false, ResponseMessage.SERVER_ERROR);
        }
    }
}
