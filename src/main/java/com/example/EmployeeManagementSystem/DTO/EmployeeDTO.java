package com.example.EmployeeManagementSystem.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeDTO {

    @NotBlank(message="Name required")
    private String name;

    @NotBlank(message = "Department required")
    private String deptName;

    @Min(value = 1000, message = "Salary must be > 1000")
    private double salary;


}
