package com.example.demo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    void deleteByNameIn(List<String> empNames);

    @Query("delete from Employee emp where emp.name in (:empNames)")
    @Modifying
    void deleteByNameQuery(@Param("empNames") List<String> empNames);

}
