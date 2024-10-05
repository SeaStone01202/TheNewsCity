package com.thenews.repository;

import com.thenews.entity.User;

import java.util.List;

public interface UserDAO {
    void save(User user);
    User findByUsername(String username);
    void delete(User user);
    void update(User user);
    List<User> findAll();
}
