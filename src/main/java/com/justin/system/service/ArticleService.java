package com.justin.system.service;

import com.justin.system.entity.request.ReqCreateArticleDTO;
import com.justin.system.entity.request.ReqUpdateArticleDTO;
import com.justin.system.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public String getArticleList() {
        return "success";
    }

    public String getArticleDetail(Integer id) {
        return id.toString();
    }

    public String createArticle(ReqCreateArticleDTO params) {
        try {
            articleRepository.save(params);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    public String updateArticle(ReqUpdateArticleDTO params) {
        try {
            articleRepository.update(params);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    public String deleteArticle(Integer id) {
        return "success";
    }
}
