package com.example.EmployeeManagementSystem.Repository;

import com.example.EmployeeManagementSystem.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpRepository extends JpaRepository<Employee, Long> {


    List<Employee> findByNameContaining(String name);
    List<Employee> findByDepartment(String department);

}
