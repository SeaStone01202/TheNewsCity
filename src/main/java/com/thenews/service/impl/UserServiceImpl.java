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
        String loginPage = "/index";
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = findByUsername(username);

        if (user != null) {
            if (BCrypt.checkpw(password, user.getPassword())) {
                request.getSession().setAttribute("user", user);
                System.out.println("Đăng nhập thành công");
            } else {
                System.out.println("Đăng nhập thất bại");
                }
        }
        servletUtil.forwardToPage(loginPage);
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

//        Part filePart = request.getPart("profilePicture");
//        // Kiểm tra filePart có hợp lệ không
//        if (filePart == null || filePart.getSize() == 0) {
//            servletUtil.showError("No file uploaded.");
//            return;
//        }
//
//        // Sử dụng ImageUtil để lưu file trên server
//        String fileName;
//        try {
//            fileName = ImageUtil.saveImage(filePart, request.getServletContext());
//        } catch (IOException e) {
//            servletUtil.showError("Error saving file: " + e.getMessage());
//            return;
//        }
//
//        // Tạo đường dẫn URL tương ứng với file đã lưu
//        String imagePath = request.getServletContext().getContextPath() + "/uploads/" + fileName;

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

}
