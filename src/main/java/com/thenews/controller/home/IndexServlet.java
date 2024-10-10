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

    private ConnectionManagement connectionManagement;

    private NewsRepository repoNews;

    private CategoryRepository repoCategory;

    private NewsService serviceNews;

    private CategoryService serviceCategory;

    private List<News> listNews;

    private List<Category> listCategory;

    @Override
    public void init() throws ServletException {
        this.connectionManagement = new ConnectionManagement();

        this.repoNews = new NewsRepoImpl(connectionManagement);
        this.serviceNews = new NewsServiceImpl(repoNews);
        this.listNews = new ArrayList<>();

        this.repoCategory = new CategoryRepoImpl(connectionManagement);
        this.serviceCategory = new CategoryServiceImpl(repoCategory);
        this.listCategory = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        listCategory = serviceCategory.findAll();
        req.setAttribute("listCategory", listCategory);

        listNews = serviceNews.findAll();
        req.setAttribute("listNews", listNews);

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
