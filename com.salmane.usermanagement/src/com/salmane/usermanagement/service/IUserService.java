package com.salmane.usermanagement.service;

import com.salmane.usermanagement.model.Role;
import com.salmane.usermanagement.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IUserService {
    List<User> get();
    User create(String name, String email);
    User create(String name, String email, Set<Role> roles);
    Optional<User> get(String id);
    boolean update(String id, User toUpdate);
    boolean delete(String id);
    boolean assignRoles(String userId, Set<Role> roles);
}
