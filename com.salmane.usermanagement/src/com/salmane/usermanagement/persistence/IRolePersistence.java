package com.salmane.usermanagement.persistence;

import com.salmane.core.persistence.IPersistenceService;
import com.salmane.usermanagement.model.Role;

public interface IRolePersistence extends IPersistenceService<Role> {
    Role getByName(String name);
}
