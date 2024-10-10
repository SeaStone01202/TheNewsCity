package com.thenews.service.impl;

import com.thenews.entity.User;
import com.thenews.repository.UserRepository;
import com.thenews.repository.impl.UserRepoImpl;
import com.thenews.service.UserService;
import com.thenews.utils.ConnectionManagement;
import com.thenews.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
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
        String loginPage = "/index";
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = findByUsername(username);

        if (user != null) {
            if (BCrypt.checkpw(password, user.getPassword())) {
                servletUtil.forwardToPage(loginPage);
                System.out.println("Dang nhap thanh cong");
            } else {
                System.out.println("Dang nhap that bai");
                return;
            }
        }
    }

    public static void main(String[] args) {
        String password = "12345";

        String password2 = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(password2);
    }
}
