package com.justin.system.repository;

import com.justin.system.entity.request.ReqCreateArticleDTO;
import com.justin.system.entity.request.ReqUpdateArticleDTO;
import com.justin.system.models.Article;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ArticleRepository extends Repository<Article, Integer> {

    Article findById(Integer id);

    List<Article> findAll();

    String save(ReqCreateArticleDTO params);

    String update(ReqUpdateArticleDTO params);
}
