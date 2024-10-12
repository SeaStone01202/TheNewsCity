package com.thenews.controller.admin;

import com.thenews.service.impl.CategoryServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/category/detail")
public class CategoryDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("ids");
        if (id.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/admin/category");
        } else {
            CategoryServiceImpl service = new CategoryServiceImpl(req, resp);
            service.selectCategory();
        }
    }
}
