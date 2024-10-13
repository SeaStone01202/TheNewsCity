package com.thenews.controller.home;

import com.thenews.entity.Category;
import com.thenews.entity.News;
import com.thenews.entity.User;
import com.thenews.service.CategoryService;
import com.thenews.service.NewsService;
import com.thenews.service.impl.CategoryServiceImpl;
import com.thenews.service.impl.NewsServiceImpl;
import com.thenews.service.impl.UserServiceImpl;
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
        CategoryService categoryService = new CategoryServiceImpl(req, resp);
        List<Category> categoryList = categoryService.findAll();
        req.setAttribute("listCategory", categoryList);

        NewsService newsService = new NewsServiceImpl(req, resp);
        List<News> newsList = newsService.findAll();
        List<News> listResponse = new ArrayList<News>();

        UserServiceImpl serviceUser = new UserServiceImpl(req, resp);
        List<User> userList = serviceUser.findAll();
        for (News news : newsList) {
            User user = serviceUser.findById(Integer.parseInt(news.getAuthorId()));
            if (user != null) {
                news.setAuthorId(user.getFullname());
            }
            if (news.getIsHome()){
                listResponse.add(news);
            }
        }
        req.setAttribute("listResponse", listResponse);
        req.setAttribute("listNews", newsList);

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
