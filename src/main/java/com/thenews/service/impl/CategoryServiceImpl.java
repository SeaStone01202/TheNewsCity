package com.thenews.service.impl;

import com.thenews.entity.Category;
import com.thenews.repository.CategoryRepository;
import com.thenews.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl  implements CategoryService {

    private final CategoryRepository repo;

    public CategoryServiceImpl(CategoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public Category save(Category entity) {
        return repo.save(entity);
    }

    @Override
    public Category update(Category entity) {
        return repo.update(entity);
    }

    @Override
    public boolean delete(Integer integer) {
        return repo.delete(integer);
    }

    @Override
    public List<Category> findAll() {
        return repo.findAll();
    }

//    public Category findById(Integer integer) {
//        return repo.findById(integer);
//    }
}
