package com.springboot.javatestingapplication.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.javatestingapplication.model.Employee;
import com.springboot.javatestingapplication.service.EmployeeService;

@WebMvcTest
public class EmployeeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private EmployeeService employeeService;

  @Autowired
  private ObjectMapper objectMapper;

  @DisplayName("save employee object")
  @Test
  public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {

    // given - precondition or setup
    Employee employee = Employee.builder()
        .firstName("abc")
        .lastName("def")
        .email("abc@gmail.com")
        .build();

    // when - action or behaviour that we are going to test

    // then - verify the output
  }

}
