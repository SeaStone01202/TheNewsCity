package com.thenews.controller.filter;

import com.thenews.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/user/*")
public class UserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        User userchcek = (User) ((HttpServletRequest) request).getSession().getAttribute("user");
        if (userchcek == null) {
            ((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/index");
        } else {
            if (!userchcek.isRole()) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/index");
            }
        }
    }
}
