package com.example.saralsh2.service.impl;

import com.example.saralsh2.base.ApiResponse;
import com.example.saralsh2.dto.CalculationTableDto;
import com.example.saralsh2.entity.CalculationTable;
import com.example.saralsh2.entity.Employee;
import com.example.saralsh2.entity.Organization;
import com.example.saralsh2.entity.Region;
import com.example.saralsh2.repository.CalculationTypeRepository;
import com.example.saralsh2.repository.EmployeeRepository;
import com.example.saralsh2.repository.OrganizationRepository;
import com.example.saralsh2.repository.RegionRepository;
import com.example.saralsh2.service.CalculationTableService;
import com.example.saralsh2.utils.ResponseMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalculationTableServiceImpl implements CalculationTableService {
    private final CalculationTypeRepository calculationTypeRepository;
    private final EmployeeRepository employeeRepository;
    private final OrganizationRepository organizationRepository;
    private final RegionRepository regionRepository;

    public CalculationTableServiceImpl(CalculationTypeRepository calculationTypeRepository, EmployeeRepository employeeRepository, OrganizationRepository organizationRepository, RegionRepository regionRepository) {
        this.calculationTypeRepository = calculationTypeRepository;
        this.employeeRepository = employeeRepository;
        this.organizationRepository = organizationRepository;
        this.regionRepository = regionRepository;
    }

    @Override
    public ApiResponse<?> create(CalculationTableDto dto) {
        try {
            if (dto.getEmployeeId() == null || dto.getOrganizationId() == null ||
                    dto.getAmount() == null || dto.getDate() == null || dto.getRate() == null ||
                    dto.getCalculationType() == null)
                return new ApiResponse<>(false, ResponseMessage.OBJECT_IS_NULL);
            Optional<Employee> employeeById = employeeRepository.findById(dto.getEmployeeId());
            Optional<Organization> byId = organizationRepository.findById(dto.getOrganizationId());
            if (employeeById.isEmpty())
                return new ApiResponse<>(false, ResponseMessage.EMPLOYEE_NOT_FOUND);
            if (byId.isEmpty())
                return new ApiResponse<>(false, ResponseMessage.ORGANIZATION_NOT_FOUND);
            Employee employee = employeeById.get();
            Organization organization = byId.get();
            CalculationTable entity = CalculationTable.toEntity(dto, employee, organization);
            CalculationTable save = calculationTypeRepository.save(entity);
            CalculationTableDto calculationTableDto = CalculationTableDto.toDto(save);
            return new ApiResponse<>(true, ResponseMessage.SUCCESS, calculationTableDto);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, ResponseMessage.SERVER_ERROR);
    }

    @Override
    public ApiResponse<?> edit(CalculationTableDto dto) {
        try {
            if (dto.getId() == null)
                return new ApiResponse<>(false, ResponseMessage.OBJECT_IS_NULL);
            Optional<Organization> byId = organizationRepository.findById(dto.getOrganizationId());
            Optional<Employee> employeeOptional = employeeRepository.findById(dto.getEmployeeId());
            Optional<CalculationTable> calculationTableOptional = calculationTypeRepository.findById(dto.getId());
            if (calculationTableOptional.isEmpty())
                return new ApiResponse<>(false, ResponseMessage.CALCULATION_TABLE_NOT_FOUND);
            if (byId.isEmpty())
                return new ApiResponse<>(false, ResponseMessage.ORGANIZATION_NOT_FOUND);
            if (employeeOptional.isEmpty())
                return new ApiResponse<>(false, ResponseMessage.EMPLOYEE_NOT_FOUND);
            Organization organization = byId.get();
            Employee employee = employeeOptional.get();
            CalculationTable calculationTable = calculationTableOptional.get();
            CalculationTable updateEntity = CalculationTable.toUpdateEntity(calculationTable, dto, organization, employee);
            CalculationTableDto calculationTableDto = CalculationTableDto.toDto(updateEntity);
            return new ApiResponse<>(true, ResponseMessage.SUCCESS, calculationTableDto);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, ResponseMessage.SERVER_ERROR);
    }

    @Override
    public ApiResponse<?> getAll() {
        try {
            List<CalculationTableDto> calculationTableDtoList = CalculationTableDto.toDtoList(calculationTypeRepository.findAll());
            return new ApiResponse<>(true, ResponseMessage.SUCCESS, calculationTableDtoList);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return new ApiResponse<>(false, ResponseMessage.SERVER_ERROR);
    }

    @Override
    public ApiResponse<?> getById(Long id) {
        try {
            if (id == null)
                return new ApiResponse<>(false, ResponseMessage.OBJECT_IS_NULL);
            Optional<CalculationTable> calculationTableOptional = calculationTypeRepository.findById(id);
            if (calculationTableOptional.isEmpty()) {
                return new ApiResponse<>(false, ResponseMessage.CALCULATION_TABLE_NOT_FOUND);
            }
            CalculationTable calculationTable = calculationTableOptional.get();
            CalculationTableDto calculationTableDto = CalculationTableDto.toDto(calculationTable);
            return new ApiResponse<>(true, ResponseMessage.SUCCESS, calculationTableDto);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, ResponseMessage.SERVER_ERROR);
    }

    @Override
    public ApiResponse<?> delete(Long id) {
        try {
            if (id == null)
                return new ApiResponse<>(false, ResponseMessage.OBJECT_IS_NULL);
            Optional<CalculationTable> calculationTableOptional = calculationTypeRepository.findById(id);
            if (calculationTableOptional.isEmpty()) {
                return new ApiResponse<>(false, ResponseMessage.CALCULATION_TABLE_NOT_FOUND);
            }
            calculationTypeRepository.deleteById(id);
            return new ApiResponse<>(true, ResponseMessage.DELETE);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, ResponseMessage.SERVER_ERROR);
    }

    @Override
    public ApiResponse<?> tsk3() {
//        try {
//            List<Region> allRegions = regionRepository.findAll();
//            List<Employee> allEmployees = employeeRepository.findAll();
//            List<Organization> allOrganizations = organizationRepository.findAll();
//            List<CalculationTable> allCalculationTables = calculationTypeRepository.findAll();
//        }
        return null;
    }
}
