package com.thenews.controller.home;

import com.thenews.entity.NewsLetter;
import com.thenews.service.impl.NewsLetterServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/index/subrision")
public class NewsLetterSubrisionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("newsletter1");
        NewsLetterServiceImpl service = new NewsLetterServiceImpl(req, resp);
        try {
            if (!email.isEmpty()) {
                NewsLetter entity = new NewsLetter();
                entity.setEmail(email);
                entity.setEnabled(true);
                service.save(entity);
                req.getSession().setAttribute("message", "Đăng ký nhận bản tin thành công");
                resp.sendRedirect(req.getContextPath() + "/index");
            } else {
                req.getSession().setAttribute("errorMessage", "Có lỗi đã xảy ra!!!");
                resp.sendRedirect(req.getContextPath() + "/index");
            }
        } catch (Exception e) {
            req.getSession().setAttribute("errorMessage", "Email đã tồn tại");
            resp.sendRedirect(req.getContextPath() + "/index");
        }

    }
}
