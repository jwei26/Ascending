package org.example.service;

import org.example.model.Department;
import org.example.repository.IDepartmentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private IDepartmentDAO departmentDAO;

    public List<Department> getDepartments() {
        return departmentDAO.getDepartments();
    }
    public Department getById(long id) { return departmentDAO.getById(id); }
    public void save(Department department){
        departmentDAO.save(department);
    }

    public void updateName(long id, String name) {
        departmentDAO.updateName(id, name);
    }

    public void deleteDepartments(long id) {
        departmentDAO.deleteDepartments(id);
    }
}
