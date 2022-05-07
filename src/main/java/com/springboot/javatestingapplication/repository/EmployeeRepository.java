package com.springboot.javatestingapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.javatestingapplication.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  Optional<Employee> findByEmail(String email);

  // define custom Query using jpql with index parameters
  @Query("Select e from Employee e where e.firstName = ?1 and e.lastName = ?2")
  Employee findByCustomQuery(String firstName, String LastName);

  // define custom query using jpql with named parametes
  @Query("Select e from Employee e where e.firstName = :firstName and e.lastName = :lastName")
  Employee findByCustomQuery1(@Param("firstName") String firstName, @Param("lastName") String LastName);
  
  //define custom query using native sql with index parameters
  @Query(value = "select * from employees e where e.first_Name =?1 and e.last_Name = ?2", nativeQuery = true)
  Employee findByNativeQuery(String firstName, String LastName);
  
  // define custom query using native sql with named parametes
  @Query(value = "select * from employees e where e.first_Name = :firstName and e.last_Name = :lastName", nativeQuery = true)
  Employee findByNativeQuery1(@Param("firstName") String firstName, @Param("lastName") String LastName);
}
