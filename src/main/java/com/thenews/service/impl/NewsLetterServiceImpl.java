package com.thenews.service.impl;

import com.thenews.entity.NewsLetter;
import com.thenews.repository.NewsLetterRepository;
import com.thenews.service.NewsLetterService;

import java.util.List;

public class NewsLetterServiceImpl implements NewsLetterService {

    private final NewsLetterRepository repo;

    public NewsLetterServiceImpl(NewsLetterRepository repo) {
        this.repo = repo;
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

    @Override
    public NewsLetter findById(Integer integer) {
        return repo.findById(integer);
    }
}
