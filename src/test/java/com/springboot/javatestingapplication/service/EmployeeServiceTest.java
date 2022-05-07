package com.springboot.javatestingapplication.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.springboot.javatestingapplication.exception.ResourceNotFoundException;
import com.springboot.javatestingapplication.model.Employee;
import com.springboot.javatestingapplication.repository.EmployeeRepository;
import com.springboot.javatestingapplication.service.impl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

  @Mock
  private EmployeeRepository employeeRepository;

  @InjectMocks
  private EmployeeServiceImpl employeeService;
  
  private Employee employee;

  @BeforeEach
  public void setup() {
    
    employee = Employee.builder()
        .id(1l)
        .firstName("mohammedNaveed")
        .lastName("Ahmed")
        .email("abc@gmail.com")
        .build();
  }
  
  @DisplayName("junit test for save employee")
  @Test
  public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject() {

    // given - precondition or setup
    given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.empty());
    given(employeeRepository.save(employee)).willReturn(employee);

    // when - action or behaviour that we are going to test
    Employee fetchedEmployee = employeeService.saveEmployee(employee);

    // then - verify the output
    assertThat(fetchedEmployee).isNotNull();
  }
  
  @DisplayName("junit test for save employee which throws exception")
  @Test
  public void givenExistingEmail_whenSaveEmployee_thenThrowException() {

    // given - precondition or setup
    given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.of(employee));
    
    // when - action or behaviour that we are going to test
    assertThrows(ResourceNotFoundException.class, () -> {
      employeeService.saveEmployee(employee);
    });

    // then - verify the output
    verify(employeeRepository, never()).save(any(Employee.class));
  }
  
  @DisplayName("junit test to retrive all employees")
  @Test
  public void givenEmployeesList_whenGetAll_thenReturnEmployeeList() {

    //given - precondition or setup
    
    Employee employee1 = Employee.builder()
        .id(1l)
        .firstName("mohammedNaveed")
        .lastName("Ahmed")
        .email("abc@gmail.com")
        .build();
    
    given(employeeRepository.findAll()).willReturn(List.of(employee,employee1));
    
    //when - action or behaviour that we are going to test
    List<Employee> employees = employeeService.getAllEmployees();

    //then - verify the output
    assertThat(employees).isNotNull();
    assertThat(employees.size()).isEqualTo(2);
  }
  
  
  //negative scenario
  @DisplayName("junit test for not to retrive all employees")
  @Test
  public void givenEmptyEmployeesList_whenGetAll_thenReturnEmptyList() {

    //given - precondition or setup
    given(employeeRepository.findAll()).willReturn(Collections.emptyList());
    
    //when - action or behaviour that we are going to test
    List<Employee> employees = employeeService.getAllEmployees();

    //then - verify the output
    assertThat(employees).isEmpty();
    assertThat(employees.size()).isEqualTo(0);
  }
  
  @DisplayName("junit test to get employee by id")
  @Test
  public void givenEmployeeId_whenFindById_thenReturnEmployeeObject() {

    //given - precondition or setup
    given(employeeRepository.findById(1l)).willReturn(Optional.of(employee));
    
    //when - action or behaviour that we are going to test
    Employee fetchedEmployee = employeeService.getEmployeeById(employee.getId());

    //then - verify the output
    assertThat(fetchedEmployee).isNotNull();
  }
  
  @DisplayName("junit test not to get employee by id")
  @Test
  public void givenEmployeeId_whenFindById_thenReturnEmptyObject() {

    //given - precondition or setup
    given(employeeRepository.findById(3l)).willThrow(new ResourceNotFoundException("employee not found with Id:3"));
    
    //when - action or behaviour that we are going to test
   ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,() ->employeeService.getEmployeeById(3l));

    //then - verify the output
   Assertions.assertEquals("employee not found with Id:3",exception.getMessage());
   
  }
  
  @DisplayName("junit test for update employee")
  @Test
  public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {

    //given - precondition or setup
    given(employeeRepository.save(employee)).willReturn(employee);
    employee.setId(5l);
    employee.setFirstName("king");
    
    //when - action or behaviour that we are going to test
    Employee updatedEmployee = employeeService.updateEmployee(employee);
    
    //then - verify the output
    assertThat(updatedEmployee).isNotNull();
    assertThat(updatedEmployee.getId()).isEqualTo(5);
    assertThat(updatedEmployee.getFirstName()).isEqualTo("king");
  }
  
  @DisplayName("junit test for delete employee")
  @Test
  public void givenEmployeeId_whenDeleteById_thenReturnNothing() {

    //given - precondition or setup
    BDDMockito.willDoNothing().given(employeeRepository).deleteById(1l);
    
    //when - action or behaviour that we are going to test
    employeeService.deleteEmployee(employee.getId());

    //then - verify the output
    verify(employeeRepository,times(1)).deleteById(employee.getId());
  }
}
