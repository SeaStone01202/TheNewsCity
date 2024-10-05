package com.thenews.repository;

import com.thenews.entity.User;
import java.util.List;

public interface UserDAO {

    void save(User user);

    void update(User user);

    void delete(User user);

    List<User> findAll();

    User findById(int id);

    User findByUsername(String username);
}
