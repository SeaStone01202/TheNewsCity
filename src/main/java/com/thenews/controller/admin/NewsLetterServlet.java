package com.thenews.controller.admin;

import com.thenews.entity.NewsLetter;
import com.thenews.service.impl.NewsLetterServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/news_letter")
public class NewsLetterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsLetterServiceImpl service = new NewsLetterServiceImpl(req, resp);
        service.getAllList();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsLetterServiceImpl service = new NewsLetterServiceImpl(req, resp);
        String id = req.getParameter("idEmail");
        String action = req.getParameter("action");
        if (action.equals("save")) {
            service.createNewsLetter();
        } else if (action.equals("delete")) {
            if (id != null || id.trim().length() > 0 || id.isEmpty()) {
                service.deleteNewsLetter();
            } else {
                req.getSession().setAttribute("message", "Can't not find Id");
                resp.sendRedirect(req.getContextPath() + "/admin/news_letter");
            }
        } else {

        }
    }
}
