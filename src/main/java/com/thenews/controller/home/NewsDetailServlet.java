package com.thenews.controller.home;

import com.thenews.repository.impl.NewsRepoImpl;
import com.thenews.service.NewsService;
import com.thenews.service.impl.NewsServiceImpl;
import com.thenews.utils.ConnectionManagement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/news_detail/*")
public class NewsDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsServiceImpl service = new NewsServiceImpl(req, resp);
        service.getNewsDetail();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
