package com.thenews.controller.home;

import com.thenews.entity.Category;
import com.thenews.entity.News;
import com.thenews.repository.CategoryRepository;
import com.thenews.repository.NewsRepository;
import com.thenews.repository.impl.CategoryRepoImpl;
import com.thenews.repository.impl.NewsRepoImpl;
import com.thenews.service.CategoryService;
import com.thenews.service.NewsService;
import com.thenews.service.impl.CategoryServiceImpl;
import com.thenews.service.impl.NewsServiceImpl;
import com.thenews.utils.ConnectionManagement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        CategoryService categoryService = new CategoryServiceImpl();
//
//        listCategory = serviceCategory.findAll();
//        req.setAttribute("listCategory", listCategory);

        NewsService newsService = new NewsServiceImpl(req, resp);
        List<News> newsList = newsService.findAll();
        req.setAttribute("listNews", newsList);

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
