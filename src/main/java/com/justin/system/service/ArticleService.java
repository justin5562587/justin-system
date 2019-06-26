package com.justin.system.service;

import com.justin.system.models.Article;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    public String getArticleList() {
        return "success";
    }

    public String getArticleDetail(Integer id) {
        return id.toString();
    }

    public String createArticle(Article article) {
        return "success";
    }

    public String updateArticle(Article article) {
        return "success";
    }

    public String deteleArticle(Integer id) {
        return "success";
    }

}
