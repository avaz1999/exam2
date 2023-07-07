package com.example.saralsh2.service.impl;

import com.example.saralsh2.base.ApiResponse;
import com.example.saralsh2.dto.EmployeeDto;
import com.example.saralsh2.entity.Employee;
import com.example.saralsh2.entity.Organization;
import com.example.saralsh2.repository.EmployeeRepository;
import com.example.saralsh2.repository.OrganizationRepository;
import com.example.saralsh2.service.EmployeeService;
import com.example.saralsh2.utils.ResponseMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final OrganizationRepository organizationRepository;
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(OrganizationRepository organizationRepository, EmployeeRepository employeeRepository) {
        this.organizationRepository = organizationRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ApiResponse<?> create(EmployeeDto dto) {
        try {
            if (dto.getFirstName() == null || dto.getLastName() == null || dto.getPinfl() == null || dto.getHireDate() == null)
                return new ApiResponse<>(false, ResponseMessage.OBJECT_IS_NULL);
            if (dto.getOrganizationId() == null)
                return new ApiResponse<>(false, ResponseMessage.ORGANIZATION_IS_NLL);
            String pinfl = dto.getPinfl().toString();
            if (pinfl.length() != 14)
                return new ApiResponse<>(false, ResponseMessage.PINFL_SIZE);
            Optional<Organization> organizationById = organizationRepository.findById(dto.getOrganizationId());
            if (organizationById.isEmpty())
                return new ApiResponse<>(false, ResponseMessage.ORGANIZATION_NOT_FOUND);
            Organization organization = organizationById.get();
            Employee employee = Employee.toEntity(dto, organization);
            employeeRepository.save(employee);
            EmployeeDto employeeDto = EmployeeDto.toDto(employee);
            return new ApiResponse<>(true, ResponseMessage.SUCCESS, employeeDto);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, ResponseMessage.SERVER_ERROR);
    }

    @Override
    public ApiResponse<?> edit(EmployeeDto dto) {
        try {
            if (dto.getId() == null || dto.getOrganizationId() == null)
                return new ApiResponse<>(false,ResponseMessage.OBJECT_IS_NULL);
            Optional<Employee> byId = employeeRepository.findById(dto.getId());
            if (byId.isEmpty()) {
                return new ApiResponse<>(false,ResponseMessage.EMPLOYEE_NOT_FOUND);
            }
            Optional<Organization> organizationOptional = organizationRepository.findById(dto.getOrganizationId());
            if (organizationOptional.isEmpty())
                return new ApiResponse<>(false,ResponseMessage.ORGANIZATION_NOT_FOUND);
            Organization organization = organizationOptional.get();
            Employee employee = byId.get();
            Employee editEntity = Employee.toEditEntity(employee, organization, dto);
            Employee save = employeeRepository.save(editEntity);
            EmployeeDto employeeDto = EmployeeDto.toDto(save);
            return new ApiResponse<>(true,ResponseMessage.SUCCESS,employeeDto);
        }catch (Throwable e){
            e.printStackTrace();
        }
        return new ApiResponse<>(false,ResponseMessage.SERVER_ERROR);
    }

    @Override
    public ApiResponse<?> getAll() {
        try {
            List<EmployeeDto> employeeDtoList = EmployeeDto.toDtoList(employeeRepository.findAll());
            return new ApiResponse<>(true,ResponseMessage.SUCCESS,employeeDtoList);
        }catch (Throwable e){
            e.printStackTrace();
        }
        return new ApiResponse<>(false,ResponseMessage.SERVER_ERROR);
    }

    @Override
    public ApiResponse<?> getById(Long id) {
        try {
            if (id == null)
                return new ApiResponse<>(false,ResponseMessage.OBJECT_IS_NULL);
            Optional<Employee> byId = employeeRepository.findById(id);
            if (byId.isEmpty())
                return new ApiResponse<>(false,ResponseMessage.EMPLOYEE_NOT_FOUND);
            Employee employee = byId.get();
            EmployeeDto employeeDto = EmployeeDto.toDto(employee);
            return new ApiResponse<>(true,ResponseMessage.SUCCESS,employeeDto);
        }catch (Throwable e){
            e.printStackTrace();
        }
        return new ApiResponse<>(false,ResponseMessage.SERVER_ERROR);
    }

    @Override
    public ApiResponse<?> delete(Long id) {
        try {
            if (id == null)
                return new ApiResponse<>(false,ResponseMessage.OBJECT_IS_NULL);
            Optional<Employee> byId = employeeRepository.findById(id);
            if (byId.isEmpty())
                return new ApiResponse<>(false,ResponseMessage.EMPLOYEE_NOT_FOUND);
            employeeRepository.deleteById(id);
            return new ApiResponse<>(true,ResponseMessage.DELETE);
        }catch (Throwable e){
            e.printStackTrace();
        }
        return new ApiResponse<>(false,ResponseMessage.SERVER_ERROR);
    }
}
