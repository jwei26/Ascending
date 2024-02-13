package org.example.repository;

import org.example.model.Department;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IDepartmentDAO {
    List<Department> getDepartments();


    Department getById(long id);

    //Create
    void save(Department department);

    //Update
    void updateName(long id, String name);

    //Delete
    void deleteDepartments(long id);

    //Department getDepartmentEagerBy(long id);

}
