package org.example.repository;

import org.example.model.Employee;


import java.util.List;

public interface IEmployeeDao {
    List<Employee> getEmployee();
    void save(Employee employee);

    void delete(long id);

}
