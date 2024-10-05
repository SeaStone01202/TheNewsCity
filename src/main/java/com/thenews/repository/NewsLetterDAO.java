package com.thenews.repository;

import com.thenews.entity.NewsLetter;
import java.util.List;

public interface NewsLetterDAO {

    int save(NewsLetter newsletter);

    List<NewsLetter> findAll();

    NewsLetter findByEmail(String email);
}
