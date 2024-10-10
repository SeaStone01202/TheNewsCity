package com.thenews.service.impl;

import com.thenews.entity.News;
import com.thenews.repository.NewsRepository;
import com.thenews.service.NewsService;

import java.util.List;

public class NewsServiceImpl implements NewsService {

    private  NewsRepository repo;

    public NewsServiceImpl(NewsRepository repo) {
        this.repo = repo;
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

    @Override
    public News findById(Integer integer) {
        return repo.findById(integer);
    }
}
