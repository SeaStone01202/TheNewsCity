package com.thenews.controller.admin;

import com.thenews.service.impl.NewsServiceImpl;
import com.thenews.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@MultipartConfig
@WebServlet("/user/personal")
public class UserPersonalServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("deGet is called");
        req.getRequestDispatcher("/views/templates/personalUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost is called");
        String validate = (String) req.getSession().getAttribute("codeMail");
        System.out.println("validate: " + validate);
        String userInputCodeValidate = req.getParameter("verificationCode");
        if (validate.equals(userInputCodeValidate)) {
            UserServiceImpl service = new UserServiceImpl(req, resp);
            service.updateUser();
        }
    }
}
