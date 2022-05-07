package com.springboot.javatestingapplication.service;

import java.util.List;
import java.util.Optional;

import com.springboot.javatestingapplication.model.Employee;

public interface EmployeeService {
  
  Employee saveEmployee(Employee employee);
  
  List<Employee> getAllEmployees();

  Employee getEmployeeById(Long id);
  
  Employee updateEmployee(Employee employee);
  
  void deleteEmployee(Long id);
}
