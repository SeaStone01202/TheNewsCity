package com.thenews.controller.admin;

import com.thenews.service.impl.NewsLetterServiceImpl;
import com.thenews.service.impl.NewsServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/news_letter/detail")
public class UserDetailNewsLetterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        NewsLetterServiceImpl service = new NewsLetterServiceImpl(req, resp);
        service.selectNewsLetter();
    }

}
