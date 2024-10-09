package com.thenews.service;

import java.util.List;

public interface GenericService <T, ID> {

    T create(T entity);

    T update(T entity);

    T delete(ID id);

    List<T> findAll();
}
