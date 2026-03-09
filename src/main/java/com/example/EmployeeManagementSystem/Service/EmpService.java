package com.example.EmployeeManagementSystem.Service;

import com.example.EmployeeManagementSystem.DTO.EmployeeDTO;
import com.example.EmployeeManagementSystem.Entity.Employee;
import com.example.EmployeeManagementSystem.Repository.EmpRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpService {
    @Autowired
    private final EmpRepository empRepository;

    @Transactional
    public Employee addEmployee(EmployeeDTO dto) {
        Employee emp = new Employee();

        emp.setName(dto.getName());
        emp.setDepartment(dto.getDeptName());
        emp.setSalary(dto.getSalary());
        emp.setDate(dto.getDate());

        return empRepository.save(emp);
    }

    public Page<Employee> getEmployees(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return empRepository.findAll(pageable);
    }

    public List<Employee> searchByName(String name) {
        return empRepository.findByNameContaining(name);
    }

    public List<Employee> searchByDepartment(String dept) {

        return empRepository.findByDepartment(dept);
    }

    @Transactional
    public Employee updateEmployee(Long id, EmployeeDTO dto) {
        Employee emp = empRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        emp.setName(dto.getName());
        emp.setDepartment(dto.getDeptName());
        emp.setSalary(dto.getSalary());

        return empRepository.save(emp);
    }

    public void deleteEmployee(Long id) {
        empRepository.deleteById(id);
    }

    public  Employee getEmployeeById(long id) {
        return empRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }
}
