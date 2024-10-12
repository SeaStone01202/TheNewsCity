package com.thenews.service.impl;

import com.thenews.entity.NewsLetter;
import com.thenews.repository.NewsLetterRepository;
import com.thenews.repository.impl.NewsLetterRepoImpl;
import com.thenews.service.NewsLetterService;
import com.thenews.utils.ConnectionManagement;
import com.thenews.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsLetterServiceImpl implements NewsLetterService {

    private ConnectionManagement connectionManagement;

    private final NewsLetterRepository repo;

    private HttpServletRequest request;

    private HttpServletResponse response;

    private ServletUtil servletUtil;

    private List<NewsLetter> newsLetterList;

    public NewsLetterServiceImpl(HttpServletRequest request, HttpServletResponse response) {
        this.connectionManagement = new ConnectionManagement();
        this.repo = new NewsLetterRepoImpl(connectionManagement);
        this.request = request;
        this.response = response;
        this.servletUtil = new ServletUtil(request, response);
        this.newsLetterList = new ArrayList<>();
    }

    @Override
    public NewsLetter save(NewsLetter entity) {
        return repo.save(entity);
    }

    @Override
    public NewsLetter update(NewsLetter entity) {
        return repo.update(entity);
    }

    @Override
    public boolean delete(Integer integer) {
        return repo.delete(integer);
    }

    @Override
    public List<NewsLetter> findAll() {
        return repo.findAll();
    }

    public void getAllList() throws ServletException, IOException {
        newsLetterList = repo.findAll();
        request.setAttribute("newsLetterList", newsLetterList);
        servletUtil.forwardToPage("/views/templates/newsLetter.jsp");
    }

    public NewsLetter findById(Integer integer) throws ServletException, IOException {
        return repo.findAll().stream()
                .filter(newsLetter -> newsLetter.getNewsletterId().equals(integer))
                .findFirst().orElse(null);
    }

    public void createNewsLetter() throws ServletException, IOException {
        String email = request.getParameter("emailAddress");
        String result = request.getParameter("subscriptionStatus");

        NewsLetter newsLetter = new NewsLetter();
        newsLetter.setEmail(email);
        newsLetter.setEnabled(Boolean.parseBoolean(result));

        NewsLetter saved = repo.save(newsLetter);
        if (saved != null) {
            request.getSession().setAttribute("message", "NewsLetter save successfully!");
            response.sendRedirect(request.getContextPath() + "/admin/news_letter");
        } else {
            request.getSession().setAttribute("errorMessage", "NewsLetter save failed!");
            response.sendRedirect(request.getContextPath() + "/admin/news_letter");
        }
    }

    public void deleteNewsLetter() throws ServletException, IOException {
        String idEmail = request.getParameter("idEmail");
        if (idEmail != null) {
            delete(Integer.parseInt(idEmail));
            NewsLetter newsLetter = findById(Integer.parseInt(idEmail));
            if (newsLetter == null) {
                request.getSession().setAttribute("message", "NewsLetter delete successfully!");
                response.sendRedirect(request.getContextPath() + "/admin/news_letter");
            } else {
                request.getSession().setAttribute("errorMessage", "NewsLetter delete failed!");
                request.getRequestDispatcher(request.getContextPath() + "/admin/news_letter").forward(request, response);
            }
        }
    }

    public void selectNewsLetter() throws ServletException, IOException {
        String idEmail = request.getParameter("id");

        // Load danh sách email lại để chắc chắn bảng không bị mất dữ liệu
        newsLetterList = repo.findAll();
        request.setAttribute("newsLetterList", newsLetterList); // Gán lại danh sách vào request

        if (idEmail != null) {
            NewsLetter entity = findById(Integer.parseInt(idEmail));
            request.setAttribute("newsLetter", entity);
            servletUtil.forwardToPage("/views/templates/newsLetter.jsp");
        } else {
            request.getSession().setAttribute("errorMessage", "NewsLetter select failed!");
            servletUtil.forwardToPage("/views/templates/newsLetter.jsp");
        }
    }

}
