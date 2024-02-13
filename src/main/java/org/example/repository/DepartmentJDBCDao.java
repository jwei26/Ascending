package org.example.repository;

import org.example.model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentJDBCDao implements IDepartmentDAO{
    private static final String DB_URL = "jdbc:postgresql://localhost:5431/training_db";
    private static final String USER = "admin";
    private static final String PASS = "Training123!";

    public List<Department> getDepartments() {
        List<Department> departments = new ArrayList<Department>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql;
            sql = "SELECT * FROM departments";

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String location = rs.getString("location");

                Department department = new Department();
                department.setId(id);
                department.setName(name);
                department.setDescription(description);
                department.setLocation(location);

                departments.add(department);
            }
        } catch(SQLException e) {
            //retry
            //log.error()
        } finally {
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                //log.error
            }
        }

        return departments;
    }

    @Override
    public Department getById(long id) {
        return null;
    }

    @Override
    public void save(Department department) {

    }

    @Override
    public void updateName(long id, String name) {

    }

    @Override
    public void deleteDepartments(long id) {

    }
}
