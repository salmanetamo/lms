package com.salmane.usermanagement.service;

import com.salmane.core.error.exceptions.EntityAlreadyExistsException;
import com.salmane.core.error.exceptions.EntityNotFoundException;
import com.salmane.usermanagement.model.Role;
import com.salmane.usermanagement.persistence.IRolePersistence;

import java.util.List;
import java.util.Optional;

public class RoleService implements IRoleService {
    private final IRolePersistence rolePersistence;

    public RoleService(IRolePersistence rolePersistence) {
        this.rolePersistence = rolePersistence;
    }

    @Override
    public List<Role> get() {
        return this.rolePersistence.get();
    }

    @Override
    public Role create(String name) {
        if (this.rolePersistence.getByName(name) != null)
            throw new EntityAlreadyExistsException("Role with name: " + name + " already exists !");

        return this.rolePersistence.create(new Role(name));
    }

    @Override
    public Optional<Role> get(String id) {
        Role role = this.rolePersistence.get(id);

        return role == null ? Optional.empty() : Optional.of(role);
    }

    @Override
    public boolean update(String id, Role toUpdate) {
        if (this.get(id).isEmpty())
            throw new EntityNotFoundException("Role with id: " + id + " not found !");
        return this.rolePersistence.update(id, toUpdate);
    }

    @Override
    public boolean delete(String id) {
        if (this.get(id).isEmpty())
            throw new EntityNotFoundException("Role with id: " + id + " not found !");
        return this.rolePersistence.delete(id);
    }
}
