package com.elliemae.controller;

import com.elliemae.Repository.EmployeeDao;
import com.elliemae.exception.ResourceNotFoundException;
import com.elliemae.model.Employee;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;

/**
 * *
 *
 * @author supraja_giddaluru
 */
@Slf4j
@RestController
@RequestMapping("/rest")
@Api(
    value = "EmployeeManagementSystem",
    description = "operations pertaining to employee in employee management system")
public class EmployeeController {

  /** Employee Crud operations to be performed */
  @Autowired(required = true)
  EmployeeDao repository;

  /**
   * Gets employees list.
   *
   * @return the employees list
   * @throws ResourceNotFoundException the resource not found exception
   */
  @ApiOperation(value = "View a list of available employees", response = List.class)
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(
            code = 403,
            message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
      })
  @GetMapping(path = "/getEmployeesList", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Employee> getEmployeesList() throws ResourceNotFoundException {
    log.info(":: Entered into grtEmployee of EmployeeController :: ");
    List<Employee> employees = repository.findAll();
    List<Employee> employObj = new ArrayList<>();
    for (Employee employee : employees) {
      employObj.add(
          new Employee(
              employee.getId(),
              employee.getFName(),
              employee.getLName(),
              employee.getEmpNo(),
              employee.getMngId()));
    }
    log.info(":: Exited from getEmployee of EmployeeController :: ");
    return employObj;
  }

  /**
   * Gets employee by id.
   *
   * @param employeeId the employee id
   * @return the employee by id
   * @throws ResourceNotFoundException the resource not found exception
   */
  @GetMapping(path = "/getEmployeeById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Get Employee Data with ID")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(
            code = 403,
            message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
      })
  public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
      throws ResourceNotFoundException {
    log.info(":: Entered from getEmployeeById of EmployeeController :: ");
    Employee employee =
        repository
            .findById(employeeId)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Employee not found for this id :: " + employeeId));
    log.info(":: Exited from getEmployeeById of EmployeeController :: ");
    //return ResponseEntity.ok().body(employee);
    URI uri = URI.create("/getEmployeeById/");
    return ResponseEntity.ok().body(employee).created(uri).build();
  }

  // creating a single employee in DB

  /**
   * Create employee string.
   *
   * @param employee the employee
   * @return the string
   */
  @PostMapping(path = "/createEmployee",produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Create Employee Data ")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(
            code = 403,
            message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
      })
  public String createEmployee(@RequestBody Employee employee) {
    log.info(":: entered from createEmployee of EmployeeController :: ");
    // save a single Customer
    repository.save(
        new Employee(
            employee.getId(),
            employee.getFName(),
            employee.getLName(),
            employee.getEmpNo(),
            employee.getMngId()));
    log.info(":: exited from createEmployee of EmployeeController :: ");
    return "Customer is created";
  }

  /**
   * Create employees string.
   *
   * @return the string
   */
  //  creating bulk upload of  employees on DB
  @PostMapping(path  = "/createEmployees",produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Create Employee Data ")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(
            code = 403,
            message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
      })

  // bulk upload of employees through JPA
  public String createEmployees() {
    log.info(":: Entered into bulk upload ::");
    Employee emp1 =
        Employee.builder().fName("harshith").lName("Khan").empNo(2).mngId(2).id(1).build();
    Employee emp2 =
        Employee.builder().fName("harshith1").lName("Khan").empNo(2).mngId(2).id(2).build();
    Employee emp3 =
        Employee.builder().fName("harshith2").lName("Khan").empNo(2).mngId(2).id(3).build();
    Employee emp4 =
        Employee.builder().fName("harshith3").lName("Khan").empNo(2).mngId(2).id(4).build();

    List<Employee> employees = Arrays.asList(emp1, emp2, emp3, emp4);
    repository.saveAll(employees);
    log.info(":: Exit from bulk upload ::");
    return "Employees are inserted successfullly";
  }

  /**
   * Update employee response entity.
   *
   * @param employeeId the employee id
   * @param employeeDetails the employee details
   * @return the response entity
   * @throws ResourceNotFoundException the resource not found exception
   */
  // update an employee to DB
  @ApiOperation(value = "Update an employee")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(
            code = 403,
            message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
      })
  @PutMapping(path = "/updateEmployees/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Employee> updateEmployee(
      @ApiParam(value = "Employee Id to update employee object", required = true)
          @PathVariable(value = "id")
          Long employeeId,
      @ApiParam(value = "Update employee object", required = true) @Valid @RequestBody
          Employee employeeDetails)
      throws ResourceNotFoundException {
    log.info(":: entered from updateEmployees of EmployeeController :: ");
    Employee employee =
        repository
            .findById(employeeId)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Employee not found for this id :: " + employeeId));

    employee.setEmpNo(employeeDetails.getEmpNo());
    employee.setFName(employeeDetails.getFName());
    employee.setLName(employeeDetails.getLName());
    employee.setMngId(employeeDetails.getMngId());
    final Employee updatedEmployee = repository.save(employee);
    log.info(":: exited from updateEmployees of EmployeeController :: ");
    return ResponseEntity.ok(updatedEmployee);
  }

  /**
   * Delete employee map.
   *
   * @param employeeId the employee id
   * @return the map
   * @throws ResourceNotFoundException the resource not found exception
   */
  // delete operation on employee on DB
  @ApiOperation(value = "Delete an employee")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(
            code = 403,
            message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
      })
  @DeleteMapping(path = "/deleteEmployees/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
  public Map<String, Boolean> deleteEmployee(
      @ApiParam(
              value = "Employee Id from which employee object will delete from database table",
              required = true)
          @PathVariable(value = "id")
          Long employeeId)
      throws ResourceNotFoundException {
    log.info(":: enterd from deleteEmployee of EmployeeController :: ");
    Employee employee =
        repository
            .findById(employeeId)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Employee not found for this id :: " + employeeId));

    repository.delete(employee);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    log.info(":: exited from deleteEmployee of EmployeeController :: ");
    return response;
  }
}
