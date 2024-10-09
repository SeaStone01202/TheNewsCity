package com.thenews.service.impl;

import com.thenews.entity.User;
import com.thenews.repository.UserRepository;
import com.thenews.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User save(User entity) {
        return repo.save(entity);
    }

    @Override
    public User update(User entity) {
        return repo.update(entity);
    }

    @Override
    public boolean delete(Integer integer) {
        return repo.delete(integer);
    }

    @Override
    public List<User> findAll() {
        return repo.findAll();
    }

    @Override
    public User findById(Integer integer) {
        return repo.findById(integer);
    }
}
