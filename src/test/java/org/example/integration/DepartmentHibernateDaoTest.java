package org.example.integration;

import org.example.ApplicationBootStrap;
import org.example.model.Department;
import org.example.model.Employee;
import org.example.repository.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootStrap.class)
public class DepartmentHibernateDaoTest {
    @Autowired
    IDepartmentDAO departmentHiberateDao;
    @Autowired
    IEmployeeDao employeeDao;
    Employee employee1;
    Employee employee2;
    Department department;

    @Before
    public void setUp() {

        department = new Department();
        department.setName("HR");
        department.setDescription("Good");
        department.setLocation("CA");
        departmentHiberateDao.save(department);

        employee1 = new Employee();
        employee1.setDepartment(department);
        employee1.setName("Lee");
        employee1.setAddress("CA");
        employeeDao.save(employee1);

        employee2 = new Employee();
        employee2.setDepartment(department);
        employee2.setName("Jane");
        employee2.setAddress("CA");
        employeeDao.save(employee2);
    }
    @After
    public void tearDown() {
        //employeeDao.delete(employee1.getId());
        //employeeDao.delete(employee2.getId());
        departmentHiberateDao.deleteDepartments(department.getId());
    }
    @Test
    public void getDepartmentsTest() {
        DepartmentJDBCDao departmentDao = new DepartmentJDBCDao();
        List<Department> departmentList = departmentDao.getDepartments();
        List<Employee> employeesList = employeeDao.getEmployee();
        assertEquals(22, employeesList.size());
        assertEquals(15, departmentList.size());
    }
}