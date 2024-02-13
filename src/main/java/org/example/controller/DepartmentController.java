package org.example.controller;

import org.example.model.Department;
import org.example.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public List<Department> getDepartment() {
        List<Department> departments = departmentService.getDepartments();
        return departments;
    }

    @RequestMapping(value = "/department/{id}", method = RequestMethod.GET)
    public Department getDepartmentById(@PathVariable(name = "id") long id) {
        logger.info("get department by {}", id);
        return departmentService.getById(id);
    }

    @RequestMapping(value = "/{id}", params = ("name"), method = RequestMethod.PATCH)
    public void updateDepartmentName(@PathVariable(name = "id") Long id, @RequestParam("name") String name) {
        logger.info("Pass in varible id {} and name {}", id.toString(), name);
        Department department = departmentService.getById(id);
        department.setName("name");
        departmentService.updateName(id, name);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void create(@RequestBody Department department) {
        logger.info("Post a new department object {}", department.getName());
        departmentService.save(department);
    }
}
