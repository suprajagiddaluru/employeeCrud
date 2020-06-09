package com.elliemae;

import com.elliemae.mockito.EmployeeTest;
import com.elliemae.model.Employee;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * *
 *
 * @author supraja_giddaluru
 */
public class EmployeeControllerTest extends EmployeeTest {

  @Override
  @Before
  public void setUp() {
    super.setUp();
  }

  @Test
  public void getEmployeesListTest() throws Exception {
    String uri = "/rest/getEmployeesList";
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();

    int status = mvcResult.getResponse().getStatus();
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    Employee[] empList = super.mapFromJson(content, Employee[].class);
    assertTrue(empList.length > 0);
  }

  @Test
  public void getEmployeesByIdTest() throws Exception {
    String uri = "/rest/getEmployeeById/12";
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();

    int status = mvcResult.getResponse().getStatus();
    assertEquals(200, status);
  }

  @Test
  public void createEmployeeTest() throws Exception {
    String uri = "/rest/createEmployee";
    Employee emp =
        Employee.builder().fName("harshithTest").lName("Test").empNo(22).mngId(22).build();
    String inputJson = super.mapToJson(emp);
    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson))
            .andReturn();

    int status = mvcResult.getResponse().getStatus();
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
  }

  @Test
  public void createEmployeesTest() throws Exception {
    String uri = "/rest/createEmployees";
    Employee emp1 = Employee.builder().fName("bulkTest").lName("Test").empNo(22).mngId(22).build();
    Employee emp2 = Employee.builder().fName("bulkTest2").lName("Test").empNo(22).mngId(22).build();
    Employee emp3 = Employee.builder().fName("bulkTest3").lName("Test").empNo(22).mngId(22).build();
    List<Employee> list = Arrays.asList(emp1, emp2, emp3);

    String inputJson = super.mapToJson(list);
    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson))
            .andReturn();

    int status = mvcResult.getResponse().getStatus();
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
  }

  @Test
  public void updateEmployeeTest() throws Exception {
    String uri = "/rest/updateEmployees/2";
    Employee emp =
        Employee.builder().fName("harshith1").lName("Khan1").empNo(20).mngId(20).id(2).build();
    String inputJson = super.mapToJson(emp);
    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.put(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson))
            .andReturn();

    int status = mvcResult.getResponse().getStatus();
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    assertEquals(content, "Product is updated successsfully");
  }

  @Test
  public void deleteEmployee() throws Exception {
    String uri = "/rest/deleteEmployees/3";
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
    int status = mvcResult.getResponse().getStatus();
    assertEquals(200, status);
  }

}
