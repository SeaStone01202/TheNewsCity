package com.thenews.repository;

import com.thenews.entity.User;

public interface UserRepository extends GenericRepository<User, Integer> {
    User findByUsername(String username);
}
