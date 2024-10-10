package com.thenews.controller.home;

import com.thenews.entity.News;
import com.thenews.repository.NewsRepository;
import com.thenews.repository.impl.NewsRepoImpl;
import com.thenews.service.NewsService;
import com.thenews.service.impl.NewsServiceImpl;
import com.thenews.util.ConnectionManagement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/v1/home")
public class indexServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ConnectionManagement connectionManagement;

    private NewsRepository repo;

    private NewsService service;

    private List<News> list;

    @Override
    public void init() throws ServletException {
        this.connectionManagement = new ConnectionManagement();
        this.repo = new NewsRepoImpl(connectionManagement);
        this.service = new NewsServiceImpl(repo);
        this.list = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        list = service.findAll();
        req.setAttribute("list", list);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
