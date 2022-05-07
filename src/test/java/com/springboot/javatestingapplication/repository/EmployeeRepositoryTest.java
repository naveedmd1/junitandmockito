package com.springboot.javatestingapplication.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.springboot.javatestingapplication.model.Employee;

@DataJpaTest
public class EmployeeRepositoryTest {

  @Autowired
  private EmployeeRepository employeeRepository;
  
  private Employee employee;
  
  @BeforeEach
  public void setup() {
    employee = Employee.builder()
        .firstName("mohammedNaveed")
        .lastName("Ahmed")
        .email("abc@gmail.com")
        .build();
  }

  @DisplayName("junit test for save employee operation")
  @Test
  public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {
    
    //given - precondition or setup
    
    //when - action or behaviour that we are going to test
    Employee savedEmployee = employeeRepository.save(employee);
    
    //then - verify the output
    assertThat(savedEmployee).isNotNull();
    assertThat(savedEmployee.getId()).isGreaterThan(0);
  }

  @DisplayName("junit test for get All Employees")
  @Test
  public void givenEmployeesList_whenFindAll_thenReturnEmployeeList() {
    
  //given - precondition or setup
    Employee employee = Employee.builder()
        .firstName("mohammedNaveed")
        .lastName("Ahmed")
        .email("abc@gmail.com")
        .build();
    
    
    Employee employee1 = Employee.builder()
        .firstName("mohammedNaveed")
        .lastName("Ahmed")
        .email("abc@gmail.com")
        .build();
    
    employeeRepository.save(employee);
    employeeRepository.save(employee1);
    
  //when - action or behaviour that we are going to test
    List<Employee> listOfEmployees = employeeRepository.findAll();
    
  //then - verify the output
    assertThat(listOfEmployees).isNotNull();
    assertThat(listOfEmployees.size()).isEqualTo(2);
  }
  
  @DisplayName("junit test to get employee by id")
  @Test
  public void givenEmployeeObject_whengGetById_thenReturnEmployeeObject() {

    //given - precondition or setup
    
    employeeRepository.save(employee);
    
    //when - action or behaviour that we are going to test
    Employee fetchedEployee= employeeRepository.findById(employee.getId()).get();
    
    //then - verify the output
    assertThat(fetchedEployee).isNotNull();
  }
  
  @DisplayName("junit test to get employee by email")
  @Test
  public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployee() {

    //given - precondition or setup
    
    employeeRepository.save(employee);
    
    //when - action or behaviour that we are going to test
    Employee fetchedEployee = employeeRepository.findByEmail(employee.getEmail()).get();
    
    //then - verify the output
    assertThat(fetchedEployee).isNotNull();
  }
  
  @DisplayName("junit test for update employee")
  @Test
  public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {

    //given - precondition or setup
    
    employeeRepository.save(employee);
    
    //when - action or behaviour that we are going to test
    Employee fetchedEployee=employeeRepository.findById(employee.getId()).get();
    
    fetchedEployee.setFirstName("kingkhan");
    fetchedEployee.setEmail("xyz@gmail.com");
    
    Employee updatedEmployee = employeeRepository.save(fetchedEployee);
    
    //then - verify the output
    assertThat(updatedEmployee).isNotNull();
    assertThat(updatedEmployee.getEmail()).isEqualTo("xyz@gmail.com");
    assertThat(updatedEmployee.getFirstName()).isEqualTo("kingkhan");
  }
  
  
  @DisplayName("junit test for delete employee operation")
  @Test
  public void givenEmployeeObject_whenDelete_thenReturnEmployee() {

    //given - precondition or setup
    
    employeeRepository.save(employee);
    
    //when - action or behaviour that we are going to test
    employeeRepository.deleteById(employee.getId());
    Optional<Employee> fetchedEployee = employeeRepository.findById(employee.getId());
    
    //then - verify the output
    assertThat(fetchedEployee).isEmpty();
  }
  
  @DisplayName("junit test for custom query using jpql with index params")
  @Test
  public void givenFirstNameAndLastName_whenFindByJPQLIndexParams_thenReturnEmployeeObject() {

    //given - precondition or setup
    
    employeeRepository.save(employee);
    
    String firstName = "mohammedNaveed";
    String lastName = "Ahmed";
    
    //when - action or behaviour that we are going to test
    Employee fetchedEployee = employeeRepository.findByCustomQuery(firstName,lastName);

    //then - verify the output
    assertThat(fetchedEployee).isNotNull();
  }
  
  @DisplayName("junit test for custom query using jpql with Named Params")
  @Test
  public void givenFirstNameAndLastName_whenFindByJPQLnamedparams_thenReturnEmployeeObject() {

    //given - precondition or setup
    employeeRepository.save(employee);
    
    String firstName = "mohammedNaveed";
    String lastName = "Ahmed";
    
    //when - action or behaviour that we are going to test
    Employee fetchedEployee = employeeRepository.findByCustomQuery1(firstName,lastName);

    //then - verify the output
    assertThat(fetchedEployee).isNotNull();
  }
  
  @DisplayName("junit test for custom query using native sql with index Params")
  @Test
  public void givenFirstNameAndLastName_whenFindByNativeindexparams_thenReturnEmployeeObject() {

    //given - precondition or setup
    
    employeeRepository.save(employee);
    
    String firstName = "mohammedNaveed";
    String lastName = "Ahmed";
    
    //when - action or behaviour that we are going to test
    Employee fetchedEployee = employeeRepository.findByNativeQuery(firstName,lastName);

    //then - verify the output
    assertThat(fetchedEployee).isNotNull();
  }
  
  @DisplayName("junit test for custom query using native sql with named Params")
  @Test
  public void givenFirstNameAndLastName_whenFindByNativenamedparams_thenReturnEmployeeObject() {

    //given - precondition or setup
    
    employeeRepository.save(employee);
    
    String firstName = "mohammedNaveed";
    String lastName = "Ahmed";
    
    //when - action or behaviour that we are going to test
    Employee fetchedEployee = employeeRepository.findByNativeQuery1(firstName,lastName);

    //then - verify the output
    assertThat(fetchedEployee).isNotNull();
  }
}
