package com.salmane.usermanagement.persistence;

import com.salmane.core.persistence.IPersistenceService;
import com.salmane.usermanagement.model.User;


public interface IUserPersistence extends IPersistenceService<User> {
    User getByEmail(String email);
}
