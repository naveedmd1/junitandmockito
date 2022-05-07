package com.springboot.javatestingapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.javatestingapplication.model.Employee;
import com.springboot.javatestingapplication.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  public Employee createEmployee(@RequestBody Employee employee) {
    return employeeService.saveEmployee(employee);
  }

}
