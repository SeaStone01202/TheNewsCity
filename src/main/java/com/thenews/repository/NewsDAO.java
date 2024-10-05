package com.thenews.repository;

import com.thenews.entity.News;
import java.util.List;

public interface NewsDAO {

    void save(News news);

    void update(News news);

    void delete(News news);

    List<News> findAll();

    News findById(int id);

    List<News> findByTitle(String title);

    List<News> findByAuthor(String author);

    List<News> findTotalNews(int start, int count);
}
