package com.thenews.service.impl;

import com.thenews.entity.Category;
import com.thenews.repository.CategoryRepository;
import com.thenews.repository.impl.CategoryRepoImpl;
import com.thenews.service.CategoryService;
import com.thenews.utils.ConnectionManagement;
import com.thenews.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class CategoryServiceImpl  implements CategoryService {

    private ConnectionManagement connectionManagement;

    private HttpServletRequest request;

    private HttpServletResponse response;

    private final CategoryRepository repo;

    private ServletUtil servletUtil;

    public CategoryServiceImpl(HttpServletRequest request, HttpServletResponse response) {
        this.connectionManagement = new ConnectionManagement();
        this.request = request;
        this.response = response;
        this.repo = new CategoryRepoImpl(connectionManagement);
        this.servletUtil = new ServletUtil(request, response);
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


}
