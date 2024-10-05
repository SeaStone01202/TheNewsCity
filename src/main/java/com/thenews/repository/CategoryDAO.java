package com.thenews.repository;

import com.thenews.entity.Category;

import java.util.List;

public interface CategoryDAO {
    Category getCategoryById(int id);
    Category getCategoryByName(String categoryName);
    List<Category> findAll();
    void saveCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(int id);
}
