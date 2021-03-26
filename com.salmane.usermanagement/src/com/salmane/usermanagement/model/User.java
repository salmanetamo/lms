package com.salmane.usermanagement.model;

import java.util.Set;
import java.util.UUID;

public class User {
    private final String id;
    private final String name;
    private final String email;
    private Set<Role> roles;

    public User(String name, String email) {
        this(UUID.randomUUID().toString(), name, email);
    }

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public User(String name, String email, Set<Role> roles) {
        this(UUID.randomUUID().toString(), name, email, roles);
    }

    public User(String id, String name, String email, Set<Role> roles) {
        this(id, name, email);
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
