package com.example.EmployeeManagementSystem.Controller;

import com.example.EmployeeManagementSystem.DTO.EmployeeDTO;
import com.example.EmployeeManagementSystem.Entity.Employee;
import com.example.EmployeeManagementSystem.Service.EmpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmpService empService;

    @PostMapping("/addEmployee")
    public Employee add(@Valid @RequestBody EmployeeDTO dto) {
        dto.setDate(LocalDateTime.now());
        return empService.addEmployee(dto);
    }

    @GetMapping
    public Page<Employee> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return empService.getEmployees(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable long id) {
        return empService.getEmployeeById(id);
    }
    @GetMapping("/search/name")
    public List<Employee> searchName(@RequestParam String name) {
        return empService.searchByName(name);
    }


    @GetMapping("/search/dept")
    public List<Employee> searchDept(@RequestParam String dept) {
        return empService.searchByDepartment(dept);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @Valid @RequestBody EmployeeDTO dto) {
        return empService.updateEmployee(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        empService.deleteEmployee(id);
    }
}
