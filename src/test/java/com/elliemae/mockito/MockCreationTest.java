package com.elliemae.mockito;

import com.elliemae.Repository.EmployeeDao;
import com.elliemae.controller.EmployeeController;
import com.elliemae.exception.ResourceNotFoundException;
import com.elliemae.model.Employee;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockCreationTest {
  private Employee employee;
  @InjectMocks
  EmployeeController manager;

  @Mock
  EmployeeDao dao;

  @Before
  public void setupMock() {
    employee = mock(Employee.class);
    dao = mock(EmployeeDao.class);
  }

  @Test
  public void testMockCreation() {
    assertNotNull(employee);
    assertNotNull(dao);
  }
  // stubbing for DAO

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void getAllEmployeesTest() throws ResourceNotFoundException {
    List<Employee> list = new ArrayList<Employee>();
    Employee empOne = Employee.builder().fName("ox1").lName("ox").empNo(20).mngId(20).id(1).build();
    Employee empTwo = Employee.builder().fName("ox2").lName("ox").empNo(20).mngId(20).id(2).build();
    Employee empThree =
        Employee.builder().fName("ox3").lName("ox").empNo(20).mngId(20).id(3).build();

    list.add(empOne);
    list.add(empTwo);
   list.add(empThree);

    when(dao.findAll()).thenReturn(list);

    // test
    List<Employee> empList = manager.getEmployeesList();


    assertEquals(3, list.size());
    //verify(dao, times(1)).findAll();
  }

  @Test
  public void createEmployeeTest() {
    Employee emp = new Employee(10, "suresh", "Gupta", 2, 3);
    manager.createEmployee(emp);
  }
}
