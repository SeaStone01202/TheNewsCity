package com.thenews.repository;

import com.thenews.entity.NewsLetter;

import java.util.List;

public interface NewsLetterDAO {
    void save(NewsLetter newsletter);
    void update(NewsLetter newsletter);
    void delete(NewsLetter newsletter);
    List<NewsLetter> findAll();
    NewsLetter findById(int id);
}
