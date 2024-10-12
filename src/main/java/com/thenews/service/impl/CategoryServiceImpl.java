package com.thenews.service.impl;

import com.thenews.entity.Category;
import com.thenews.repository.CategoryRepository;
import com.thenews.repository.impl.CategoryRepoImpl;
import com.thenews.service.CategoryService;
import com.thenews.utils.ConnectionManagement;
import com.thenews.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl  implements CategoryService {

    private ConnectionManagement connectionManagement;

    private HttpServletRequest request;

    private HttpServletResponse response;

    private final CategoryRepository repo;

    private ServletUtil servletUtil;

    private List<Category> categories;

    public CategoryServiceImpl(HttpServletRequest request, HttpServletResponse response) {
        this.connectionManagement = new ConnectionManagement();
        this.request = request;
        this.response = response;
        this.repo = new CategoryRepoImpl(connectionManagement);
        this.servletUtil = new ServletUtil(request, response);
        this.categories = new ArrayList<>();
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

    public void createCategory() throws IOException {
        String categoryName = request.getParameter("categoryName");
        Category category = new Category();
        category.setCategoryName(categoryName);
        Category create = save(category);
        if (create != null) {
            request.getSession().setAttribute("message", "Category create successfully!");
            response.sendRedirect(request.getContextPath() + "/admin/category");
        } else {
            request.getSession().setAttribute("message", "Category create failed!");
            response.sendRedirect(request.getContextPath() + "/admin/category");
        }
    }

    public void updateCategory() throws IOException {
        String categoryId = request.getParameter("categoryId");
        String categoryName = request.getParameter("categoryName");
        Category requestCate = new Category();
        requestCate.setCategoryId(Integer.parseInt(categoryId));
        requestCate.setCategoryName(categoryName);
        Category update = update(requestCate);
        if (update != null) {
            request.getSession().setAttribute("message", "Category updated successfully!");
            response.sendRedirect(request.getContextPath() + "/admin/category");
        } else {
            request.getSession().setAttribute("message", "Category updated failed!");
            response.sendRedirect(request.getContextPath() + "/admin/category");
        }
    }

    public void deleteCategory() throws IOException {
        String categoryId = request.getParameter("categoryId");
        Category requestCate = new Category();
        requestCate.setCategoryId(Integer.parseInt(categoryId));
        if (delete(Integer.valueOf(categoryId))) {
            request.getSession().setAttribute("message", "Category deleted successfully!");
            response.sendRedirect(request.getContextPath() + "/admin/category");
        } else {
            request.getSession().setAttribute("message", "Category deleted failed!");
            response.sendRedirect(request.getContextPath() + "/admin/category");
        }
    }

    public void getAll() throws ServletException, IOException {
        categories = repo.findAll();
        request.getSession().setAttribute("listCategory", categories);
        request.getRequestDispatcher("/views/templates/category.jsp").forward(request, response);
    }

    public void selectCategory() throws IOException {
        String categoryId = request.getParameter("ids");
        Category requestCate = new Category();
        requestCate.setCategoryId(Integer.parseInt(categoryId));

        categories = repo.findAll();
        Category selectedCategory = categories.stream().filter(category -> category.getCategoryId() == Integer.parseInt(categoryId)).findFirst().get();
        if (selectedCategory != null) {
            request.getSession().setAttribute("selectedCategory", selectedCategory);
            response.sendRedirect(request.getContextPath() + "/admin/category");
        }
    }
}
