package com.thenews.entity;

import java.lang.Integer;
import java.sql.Date;

public class News {

    private Integer newsId;

    private String title;

    private String content;

    private String image;

    private Date postedDate;

    private String authorId;

    private Integer viewCount;

    private String categoryId;

    private boolean isHome;

    public News() {
    }

    public News(Integer newsId, String title, String content, String image, Date postedDate, String authorId, Integer viewCount, String categoryId, boolean isHome) {
        this.newsId = newsId;
        this.title = title;
        this.content = content;
        this.image = image;
        this.postedDate = postedDate;
        this.authorId = authorId;
        this.viewCount = viewCount;
        this.categoryId = categoryId;
        this.isHome = isHome;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public boolean getIsHome() {
        return isHome;
    }

    public void setIsHome(boolean home) {
        isHome = home;
    }
}
