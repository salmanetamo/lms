package com.salmane.usermanagement.model;

import java.util.UUID;

public class Role {
    private final String id;
    private final String name;

    public Role(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public Role(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
