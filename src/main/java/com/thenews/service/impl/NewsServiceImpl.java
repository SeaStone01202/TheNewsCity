package com.thenews.service.impl;

import com.thenews.entity.Category;
import com.thenews.entity.News;
import com.thenews.repository.NewsRepository;
import com.thenews.repository.impl.NewsRepoImpl;
import com.thenews.service.CategoryService;
import com.thenews.service.NewsService;
import com.thenews.utils.ConnectionManagement;
import com.thenews.utils.DateUtils;
import com.thenews.utils.ImageUtil;
import com.thenews.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class NewsServiceImpl implements NewsService {

    private ConnectionManagement connectionManagement;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private NewsRepository repo;
    private ServletUtil servletUtil;
    private List<News> newsList;
    private List<Category> categoryList;
    private CategoryService categoryService;


    private static final Logger logger = Logger.getLogger(NewsServiceImpl.class.getName());

    public NewsServiceImpl(HttpServletRequest request, HttpServletResponse response) {
        this.connectionManagement = new ConnectionManagement();
        this.request = request;
        this.response = response;
        this.repo = new NewsRepoImpl(connectionManagement);
        this.servletUtil = new ServletUtil(request, response);
        this.newsList = null;
        this.categoryList = null;
        this.categoryService = new CategoryServiceImpl(request, response);
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
        return repo.findAll().stream()
                .filter(news -> news.getNewsId().equals(integer))
                .findFirst()
                .orElse(null);
    }

    public void getNewsDetail() throws IOException, ServletException {
        String pathInfo = request.getPathInfo(); // Lấy phần thông tin đường dẫn
        if (pathInfo != null && pathInfo.split("/").length > 1) {
            String newsId = pathInfo.split("/")[1]; // Lấy newsId từ URL
            News news = findById(Integer.parseInt(newsId)); // Tìm tin tức theo ID

            if (news != null) {
                request.setAttribute("news", news); // Đưa tin tức vào thuộc tính request
                request.getRequestDispatcher("/views/templates/page/detail_news.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "News not found");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request format");
        }
    }

    public void createNews() throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String category = request.getParameter("category") != null ? request.getParameter("category") : "1";
        String isHome = request.getParameter("home");
        Part filePart = request.getPart("mockupID");

        // Kiểm tra filePart có hợp lệ không
        if (filePart == null || filePart.getSize() == 0) {
            servletUtil.showError("No file uploaded.");
            return;
        }

        // Sử dụng ImageUtil để lưu file trên server
        String fileName;
        try {
            fileName = ImageUtil.saveImage(filePart, request.getServletContext());
        } catch (IOException e) {
            servletUtil.showError("Error saving file: " + e.getMessage());
            return;
        }

        // Tạo đường dẫn URL tương ứng với file đã lưu
        String imagePath = request.getServletContext().getContextPath() + "/uploads/" + fileName;

        News entityResponse = new News();
        entityResponse.setTitle(title);
        entityResponse.setContent(content);
        entityResponse.setCategoryId(category);
        entityResponse.setIsHome(Boolean.parseBoolean(isHome));
        entityResponse.setPostedDate(DateUtils.getCurrentDate());
        entityResponse.setAuthorId("2");
        entityResponse.setImage(imagePath);
        News news = save(entityResponse);

        if (news != null) {
            request.getSession().setAttribute("message", "News saved successfully!");
            response.sendRedirect(request.getContextPath() + "/user/news");
        } else {
            request.getSession().setAttribute("errorMessage", "Failed to save news.");
            response.sendRedirect(request.getContextPath() + "/user/news");
        }
    }

    public void updateNews() throws ServletException, IOException {
        String id = request.getParameter("idC");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String category = request.getParameter("category");
        String isHome = request.getParameter("home");
        Part filePart = request.getPart("mockupID");

        // Kiểm tra filePart có hợp lệ không
        if (filePart == null || filePart.getSize() == 0) {
            servletUtil.showError("No file uploaded.");
            return;
        }

        // Sử dụng ImageUtil để lưu file trên server
        String fileName;
        try {
            fileName = ImageUtil.saveImage(filePart, request.getServletContext());
        } catch (IOException e) {
            servletUtil.showError("Error saving file: " + e.getMessage());
            return;
        }

        // Tạo đường dẫn URL tương ứng với file đã lưu
        String imagePath = request.getServletContext().getContextPath() + "/uploads/" + fileName;
        News entityResponse = new News();
        entityResponse.setTitle(title);
        entityResponse.setContent(content);
        entityResponse.setCategoryId(category);
        entityResponse.setIsHome(Boolean.parseBoolean(isHome));
        entityResponse.setPostedDate(DateUtils.getCurrentDate());
        entityResponse.setAuthorId("2");
        entityResponse.setImage(imagePath);
        entityResponse.setNewsId(Integer.parseInt(id));
        News news = update(entityResponse);
        if (news != null) {
            request.getSession().setAttribute("message", "News updated successfully!");
            response.sendRedirect(request.getContextPath() + "/user/news");
        } else {
            request.getSession().setAttribute("errorMessage", "Failed to update news.");
            response.sendRedirect(request.getContextPath() + "/user/news");
        }
    }

    public void deleteNews() throws ServletException, IOException {
        String id = request.getParameter("idC");
        Integer newsId = Integer.parseInt(id);
        delete(newsId);
        News news = findById(newsId);
        if (news == null) {
            request.getSession().setAttribute("message", "News delete successfully!");
            response.sendRedirect(request.getContextPath() + "/user/news");
        } else {
            request.getSession().setAttribute("errorMessage", "Failed to delete news.");
            response.sendRedirect(request.getContextPath() + "/user/news");
        }
    }

    public void selectNews() throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        newsList = repo.findAll(); // Lấy tất cả bài viết
        News selectedEntity = newsList.stream().filter(news -> news.getNewsId().equals(id)).findFirst().orElse(null);

        // Đưa bài viết đã chọn vào request
        if (selectedEntity != null) {
            request.setAttribute("selectedNews", selectedEntity);
        }

        // Cần lấy danh mục để hiển thị trên trang
        CategoryService categoryService = new CategoryServiceImpl(request, response);
        List<Category> categoryList = categoryService.findAll();
        request.setAttribute("listCategory", categoryList);

        // Đưa danh sách bài viết vào request
        request.setAttribute("listNews", newsList);

        // Chuyển tiếp đến trang JSP
        request.getRequestDispatcher("/views/templates/user.jsp").forward(request, response);
    }


    public void getAllNews() throws ServletException, IOException {
        newsList = findAll();
        request.setAttribute("listNews", newsList);

        categoryList = categoryService.findAll();
        request.setAttribute("listCategory", categoryList);

        request.getRequestDispatcher("/views/templates/user.jsp").forward(request, response);
    }
}