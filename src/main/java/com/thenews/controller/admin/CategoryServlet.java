package com.thenews.controller.admin;

import com.thenews.service.impl.CategoryServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/category")
public class CategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryServiceImpl service = new CategoryServiceImpl(req, resp);
        service.getAll();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        CategoryServiceImpl service = new CategoryServiceImpl(req, resp);
        String id = req.getParameter("categoryId");
        switch (action) {
            case "save":
                if (id.isEmpty()) {
                    req.getSession().setAttribute("errorMessage", "ID is empty");
                    resp.sendRedirect(req.getContextPath()+ "/admin/category");
                } else {
                    service.updateCategory();
                }
                break;
            case "update":
                if (id.isEmpty()) {
                    req.getSession().setAttribute("errorMessage", "ID is empty");
                    resp.sendRedirect(req.getContextPath()+ "/admin/category");
                } else {
                    service.updateCategory();
                }
                break;
            case "delete":
                if (id.isEmpty()) {
                    req.getSession().setAttribute("errorMessage", "ID is empty");
                    resp.sendRedirect(req.getContextPath()+ "/admin/category");
                } else {
                    service.deleteCategory();
                }
                break;
        }
    }
}
