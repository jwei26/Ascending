package org.example.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {
    public Employee() {}

    public Employee(String name, String firstName, String lastName, String email, String address) {
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private Set<Account> accounts;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
