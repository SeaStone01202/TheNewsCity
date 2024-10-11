package com.thenews.controller.admin;

import com.thenews.service.impl.NewsServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/news")
@MultipartConfig
public class UserNewsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8"); // Thiết lập mã hóa cho phản hồi
        req.setCharacterEncoding("UTF-8"); // Thiết lập mã hóa cho yêu cầu
        NewsServiceImpl service = new NewsServiceImpl(req, resp);
            service.getAllNews();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); // Thiết lập mã hóa cho yêu cầu
        resp.setCharacterEncoding("UTF-8"); // Thiết lập mã hóa cho phản hồi
        NewsServiceImpl service = new NewsServiceImpl(req, resp);
        String actionButton = req.getParameter("action");

        String testId = req.getParameter("idC");
        String testCategory = req.getParameter("category");
        String testTitle = req.getParameter("title");
        String testContent = req.getParameter("content");
        switch (actionButton) {
            case "save":
                if (checkValueBeforeForwarding(testCategory, testTitle, testContent)) {
                    service.createNews();
                } else {
                    req.getSession().setAttribute("errorMessage", "Error Create News");
                    resp.sendRedirect(req.getContextPath() + "/user/news");
                }
                break; // Thêm break để dừng lại ở đây

            case "update":
                if (checkValueBeforeForwarding(testId, testCategory, testTitle, testContent)) {
                    service.updateNews();
                } else {
                    req.getSession().setAttribute("errorMessage", "Can not find ID for Update");
                    resp.sendRedirect(req.getContextPath() + "/user/news");
                }
                break; // Thêm break để dừng lại ở đây

            case "delete":
                if (checkValueBeforeForwarding(testId, testCategory, testTitle, testContent)) {
                    service.deleteNews();
                } else {
                    req.getSession().setAttribute("errorMessage", "Can not find ID for Delete");
                    resp.sendRedirect(req.getContextPath() + "/user/news");
                }
                break; // Thêm break để dừng lại ở đây

            default:
                // Xử lý trường hợp không hợp lệ nếu cần thiết
                break;
        }
    }

    private boolean checkValueBeforeForwarding(String... values) {
        if (values == null || values.length == 0 || values[0].isEmpty()) {
            return false;
        }
        return true;
    }

}
