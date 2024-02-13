package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "allowed_resource")
    private String allowedResource;
    @Column(name = "allowed_read")
    private Boolean allowedRead;
    @Column(name = "allowed_create")
    private Boolean allowedCreate;
    @Column(name = "allowed_update")
    private Boolean allowedUpdate;
    @Column(name = "allowed_delete")
    private Boolean allowedDelete;
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users;

    public boolean isAllowedRead() { return this.allowedRead; }

    public void setAllowedRead(Boolean allowedRead) {
        this.allowedRead = allowedRead;
    }

    public boolean isAllowedCreate() { return this.allowedRead; }

    public void setAllowedCreate(Boolean allowedCreate) {
        this.allowedCreate = allowedCreate;
    }

    public boolean isAllowedUpdate() { return this.allowedUpdate; }

    public void setAllowedUpdate(Boolean allowedUpdate) {
        this.allowedUpdate = allowedUpdate;
    }

    public boolean isAllowedDelete() { return this.allowedDelete; }

    public void setAllowedDelete(Boolean allowedDelete) {
        this.allowedDelete = allowedDelete;
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

    public String getAllowedResource() {
        return allowedResource;
    }

    public Boolean getAllowedRead() {
        return allowedRead;
    }

    public Boolean getAllowedCreate() {
        return allowedCreate;
    }

    public Boolean getAllowedUpdate() {
        return allowedUpdate;
    }

    public Boolean getAllowedDelete() {
        return allowedDelete;
    }
}
