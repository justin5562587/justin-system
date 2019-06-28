package com.justin.system.repository;

import com.justin.system.entity.request.ReqCreateArticleDTO;
import com.justin.system.entity.request.ReqUpdateArticleDTO;
import com.justin.system.models.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Integer> {

}
