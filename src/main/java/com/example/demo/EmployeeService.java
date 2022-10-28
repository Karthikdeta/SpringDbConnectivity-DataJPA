package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public void createBulkInserts() {
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            employeeList.add(
                    Employee.builder().name("Test Name" + i).organization("Test Organization " + i)
                            .salary((long) (Math.random() * 100000)).build());
        }
        employeeRepository.saveAll(employeeList);
    }

    public void deleteEmployee(int empId) {
        employeeRepository.deleteById(empId);
    }

    @Transactional
    public void deleteEmployeesById(List<Integer> empIds) {
        employeeRepository.deleteAllById(empIds);
    }

    @Transactional
    public void deleteEmployeesByIdInBatch(List<Integer> empIds) {
        employeeRepository.deleteAllByIdInBatch(empIds);
    }

    @Transactional
    public void deleteByNameIn(List<String> employeeNames) {
        employeeRepository.deleteByNameIn(employeeNames);
    }

    @Transactional
    public void deleteByNameQuery(List<String> employeeNames) {
        employeeRepository.deleteByNameQuery(employeeNames);
    }

    @Cacheable(value = "employees")
    public Employee findEmployeeById(int empId) {
        return employeeRepository.findById(empId).get();
    }

}
