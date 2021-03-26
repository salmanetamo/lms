package com.salmane.usermanagement.service;

import com.salmane.usermanagement.model.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    List<Role> get();
    Role create(String name);
    Optional<Role> get(String id);
    boolean update(String id, Role toUpdate);
    boolean delete(String id);
}
