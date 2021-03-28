package com.salmane.usermanagement.service;

import com.salmane.core.error.exceptions.EntityAlreadyExistsException;
import com.salmane.core.error.exceptions.EntityNotFoundException;
import com.salmane.usermanagement.model.Role;
import com.salmane.usermanagement.model.User;
import com.salmane.usermanagement.persistence.IUserPersistence;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class UserService implements IUserService {
    private final IUserPersistence userPersistence;

    public UserService(IUserPersistence userPersistence) {
        this.userPersistence = userPersistence;
    }

    @Override
    public List<User> get() {
        return this.userPersistence.get();
    }

    @Override
    public User create(String name, String email) {
        return this.create(name, email, Collections.emptySet());
    }

    @Override
    public User create(String name, String email, Set<Role> roles) {
        if (this.userPersistence.getByEmail(email) != null)
            throw new EntityAlreadyExistsException("User with email: " + email + " already exists !");

        return this.userPersistence.create(new User(name, email, roles));
    }

    @Override
    public Optional<User> get(String id) {
        User user = this.userPersistence.get(id);
        return user == null ? Optional.empty() : Optional.of(user);
    }

    @Override
    public boolean update(String id, User toUpdate) {
        if (this.get(id).isEmpty())
            throw new EntityNotFoundException("User with id: " + id + " not found !");
        return this.userPersistence.update(id, toUpdate);
    }

    @Override
    public boolean delete(String id) {
        if (this.get(id).isEmpty())
            throw new EntityNotFoundException("User with id: " + id + " not found !");
        return this.userPersistence.delete(id);
    }

    @Override
    public boolean assignRoles(String userId, Set<Role> roles) {
        Optional<User> optionalUser = this.get(userId);
        if (optionalUser.isEmpty())
            throw new EntityNotFoundException("User with id: " + userId + " not found !");

        User user = optionalUser.get();
        roles.addAll(user.getRoles());
        user.setRoles(roles);
        return this.userPersistence.update(userId, user);
    }
}
