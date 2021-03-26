package com.salmane.core.persistence;

import java.util.List;

public interface IPersistenceService<T> {
    List<T> get();
    T create(T toCreate);
    T get(String id);
    boolean update(String id, T toUpdate);
    boolean delete(String id);
}
