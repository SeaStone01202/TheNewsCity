package com.thenews.repository;

import com.thenews.entity.News;

import java.util.List;

public interface NewsDAO {
     void insertNews(News news);
     void updateNews(News news);
     void deleteNews(News news);
     News getNewsById(int id);
     List<News> getAllNews();
     List<News> getAllNews(int start, int count);
     int getTotalNews();
     int getTotalNews(int start, int count);
     int getTotalNews(int start);
     int getTotalNews(int start, int count, int type);
     int getTotalNews(int start, int count, int type, int status);
}
