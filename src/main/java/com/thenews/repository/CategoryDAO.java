package com.thenews.repository;

import com.thenews.entity.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDAO {

    int save(Category entity);

    int update(Category entity);

    int delete(int id);

    List<Category> findAll();

    Category findById(int id);

    Category findByName(String categoryName);
}
