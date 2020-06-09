package com.elliemae.Repository;

import com.elliemae.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Employee dao.
 */
@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {
  /**
   * Find by id list.
   *
   * @param id the id
   * @return the list
   */
  Employee findById(long id);

  List<Employee> findAll();
}
