package com.elliemae.DB;

import com.elliemae.Repository.EmployeeDao;
import com.elliemae.model.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;

/***
 * @author supraja_giddaluru
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DbConfig.class})
@ActiveProfiles("DaoTest")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:dao/TestData.sql")
public class DbTesting {
  @Autowired private EmployeeDao empRepository;

  @Test
  public void contextLoads() {

    empRepository.save(Employee.builder().id(new Random().nextLong()).build());

    Assert.assertTrue(empRepository.findById(1) != null);
  }
}
