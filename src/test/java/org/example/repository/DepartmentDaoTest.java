package org.example.repository;

import org.example.model.Department;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DepartmentDaoTest {

    @Test
    public void getDepartmentsTest() {
        DepartmentJDBCDao departmentDao = new DepartmentJDBCDao();
        List<Department> departmentList = departmentDao.getDepartments();

        assertEquals(4, departmentList.size());
    }
}