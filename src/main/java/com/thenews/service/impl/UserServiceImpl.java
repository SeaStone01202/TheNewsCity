package com.thenews.service.impl;

import com.thenews.entity.User;
import com.thenews.repository.UserRepository;
import com.thenews.repository.impl.UserRepoImpl;
import com.thenews.service.UserService;
import com.thenews.utils.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Date;
import java.util.List;

public class UserServiceImpl implements UserService {

    private ConnectionManagement connectionManagement;

    private HttpServletRequest request;

    private HttpServletResponse response;

    private final UserRepository repoUser;

    private ServletUtil servletUtil;

    public UserServiceImpl(HttpServletRequest request, HttpServletResponse response) {
        this.connectionManagement = new ConnectionManagement();
        this.repoUser = new UserRepoImpl(connectionManagement);
        this.response = response;
        this.request = request;
        this.servletUtil = new ServletUtil(request, response);
    }

    @Override
    public User save(User entity) {
        return repoUser.save(entity);
    }

    @Override
    public User update(User entity) {
        return repoUser.update(entity);
    }

    @Override
    public boolean delete(Integer integer) {
        return repoUser.delete(integer);
    }

    @Override
    public List<User> findAll() {
        return repoUser.findAll();
    }

    public User findById(Integer id) {
        User entity = null;
        List<User> list = repoUser.findAll();
        for (User user : list) {
            if (user.getUserId().equals(id)) {
                entity = user;
            }
        }
        return entity;
    }

    private boolean checkRole (User userLogin) {
        List<User> list = repoUser.findAll();
        for (User user : list) {
            if (userLogin.isRole()) {
                return true;
            }
        }
        return false;
    }

    private User findByUsername(String username) {
        User entity = null;
        List<User> list = repoUser.findAll();
        for (User user : list) {
            if (user.getUsername().equals(username)) {
                entity = user;
                return entity;
            }
        }
        return null;
    }


    public void checkLogin() throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = findByUsername(username);

        if (user != null) {
            if (BCrypt.checkpw(password, user.getPassword())) {
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("message", "Đăng nhập thành công!");
                response.sendRedirect(request.getContextPath() + "/index");
            } else {
                request.getSession().setAttribute("errorMessage", "Sai tài khoản hoặc mật khẩu!");
                response.sendRedirect(request.getContextPath() + "/login");
                }
        }
    }


    public void updateUser() throws ServletException, IOException {
        User entity = (User) request.getSession().getAttribute("user");
        String fullname = request.getParameter("fullName");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");

        if (BCrypt.checkpw(oldPassword, entity.getPassword())) {
            entity.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
        }

        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String birthday = request.getParameter("birthday");
        System.out.println(birthday);

        entity.setFullname(fullname);
        entity.setEmail(email);
        entity.setPhone(phone);
        entity.setBirthday(Date.valueOf(birthday));
        update(entity);
         User entityCheck  = update(entity);
         if (entityCheck != null) {
             request.getSession().setAttribute("message", "User updated successfully!");
             response.sendRedirect(request.getContextPath() + "/user/personal");
         } else {
             request.getSession().setAttribute("message", "User updated error!");
             response.sendRedirect(request.getContextPath() + "/user/personal");
         }

    }

    public void selectedUser() {
        User userSelected = (User) request.getSession().getAttribute("user");

    }


    public void createUser() throws IOException {
        String username = request.getParameter("username");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User userRequest = new User();
        userRequest.setFullname(fullname);
        userRequest.setRole(false);
        userRequest.setUsername(username);
        userRequest.setEmail(email);
        userRequest.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));

        User createdUser = new User();
        try {
            createdUser = save(userRequest);
            if (createdUser != null) {
                request.getSession().setAttribute("message", "Tạo tài khoản thành công!");
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Username hoặc email đã tồn tại !");
            response.sendRedirect(request.getContextPath() + "/register");
        }
    }
}
