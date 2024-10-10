package com.thenews.repository;

import java.util.List;

public interface GenericRepository <T, ID>{
    T save(T entity);
    T update(T entity);
    boolean delete(ID id);
    List<T> findAll();
}
