package com.example.saralsh2.repository;

import com.example.saralsh2.dto.CalculateTask3Dto;
import com.example.saralsh2.entity.CalculationTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CalculationTypeRepository extends JpaRepository<CalculationTable, Long> {
    void deleteByEmployeeId(Long id);

    List<CalculationTable> findCalculationTablesByOrganizationId(Long id);

    void deleteByOrganizationId(Long id);

    @Query(nativeQuery = true, value = "" +
            "select e.first_name,e.last_name,e.pinfl,sum(ct.rate) from employee e\n" +
            "         inner join calculation_table ct on e.id = ct.employee_id\n" +
            "where date_trunc('month', date)=date_trunc('month', :date )\n" +
            "group by e.first_name, e.last_name, e.pinfl")
    List<CalculateTask3Dto> getTask3(LocalDate date);

//    @Query(value = "SELECT\n" +
//            "    e.pinfl,\n" +
//            "    SUM(c.rate) AS total_rate\n" +
//            "FROM\n" +
//            "    calculation_table c\n" +
//            "        JOIN\n" +
//            "    Employee e ON c.employee_id = e.id\n" +
//            "WHERE\n" +
//            "        c.local_date >= '2022-01-01' AND c.local_date < '2022-02-01'\n" +
//            "GROUP BY\n" +
//            "    e.pinfl")
//    List<CalculateTask3Dto> getTask3(String date);


}
