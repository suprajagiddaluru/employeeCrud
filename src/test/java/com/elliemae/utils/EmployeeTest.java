package com.elliemae.utils;

import com.elliemae.model.Employee;
import org.junit.Test;
/**
 * *
 *
 * @author supraja_giddaluru
 */
public class EmployeeTest {
  @Test
  public void testAccesors_shouldAccessProperField() {

    PojoTestUtils.validateAccessors(Employee.class);
  }
}
