package com.springboot.javatestingapplication.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.javatestingapplication.exception.ResourceNotFoundException;
import com.springboot.javatestingapplication.model.Employee;
import com.springboot.javatestingapplication.repository.EmployeeRepository;
import com.springboot.javatestingapplication.service.EmployeeService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  EmployeeRepository employeeRepository;

  @Override
  public Employee saveEmployee(Employee employee) {

    Optional<Employee> fetchedEmployee = employeeRepository.findByEmail(employee.getEmail());
    if (fetchedEmployee.isPresent()) {
      throw new ResourceNotFoundException("Email already exist with given email:" + employee.getEmail());
    }
    return employeeRepository.save(employee);
  }

  @Override
  public List<Employee> getAllEmployees() {

    return employeeRepository.findAll();

  }

  @Override
  public Employee getEmployeeById(Long id) {
    
    return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("employee not found with Id:"+id));
  }

  @Override
  public Employee updateEmployee(Employee employee) {
    
    return employeeRepository.save(employee);
  }

  @Override
  public void deleteEmployee(Long id) {
    employeeRepository.deleteById(id);   
  }

}
