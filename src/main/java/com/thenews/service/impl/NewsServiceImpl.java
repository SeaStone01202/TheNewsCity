package com.thenews.service.impl;

import com.thenews.entity.News;
import com.thenews.repository.NewsRepository;
import com.thenews.repository.impl.NewsRepoImpl;
import com.thenews.service.NewsService;
import com.thenews.utils.ConnectionManagement;
import com.thenews.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class NewsServiceImpl implements NewsService {

    private ConnectionManagement connectionManagement;

    private HttpServletRequest request;

    private HttpServletResponse response;

    private NewsRepository repo;

    private ServletUtil servletUtil;

    public NewsServiceImpl(HttpServletRequest request, HttpServletResponse response) {
        this.connectionManagement = new ConnectionManagement();
        this.request = request;
        this.response = response;
        this.repo = new NewsRepoImpl(connectionManagement);
        this.servletUtil = new ServletUtil(request, response);
    }

    @Override
    public News save(News entity) {
        return repo.save(entity);
    }

    @Override
    public News update(News entity) {
        return repo.update(entity);
    }

    @Override
    public boolean delete(Integer integer) {
        return repo.delete(integer);
    }

    @Override
    public List<News> findAll() {
        return repo.findAll();
    }

    private News findById(Integer integer) {
        News entity = null;
        List<News> list = repo.findAll();
        for (News news : list) {
            if (news.getNewsId().equals(integer)) {
                entity = news;
                return entity;
            }
        }
        return null;
    }

    public void getNewsDetail() throws IOException, ServletException {
        String pathInfo = request.getPathInfo(); // Lấy phần thông tin đường dẫn
        if (pathInfo != null && pathInfo.split("/").length > 1) {
            String newsId = pathInfo.split("/")[1]; // Lấy newsId từ URL
            News news = findById(Integer.parseInt(newsId)); // Tìm tin tức theo ID

            if (news != null) {
                request.setAttribute("news", news); // Đưa tin tức vào thuộc tính request
                request.getRequestDispatcher("/views/templates/page/detail_news.jsp").forward(request, response); // Chuyển tiếp đến trang chi tiết
                return;
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND); // Nếu không tìm thấy tin tức
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST); // Nếu không có newsId
        }
    }
}
