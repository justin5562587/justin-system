package com.justin.system.service;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.request.ReqCreateArticleDTO;
import com.justin.system.entity.request.ReqUpdateArticleDTO;
import com.justin.system.models.Article;
import com.justin.system.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public ArrayList<Article> getArticleList() {
        Iterable<Article> foundArticles = articleRepository.findAll();
        ArrayList<Article> articles = new ArrayList<>();
        for (Article article : foundArticles) {
            articles.add(article);
        }

        return articles;
    }

    public ResponseWrapper getArticleDetail(Long id) {
        Optional<Article> foundArticle = articleRepository.findById(id);
        return foundArticle.isPresent() ?
                ResponseWrapper.successRender(foundArticle) :
                ResponseWrapper.failRender("error");
    }

    @Transactional
    public String createArticle(ReqCreateArticleDTO params) {
        try {
            Article createdArticle = new Article();
            createdArticle.setTitle(params.getTitle());
            createdArticle.setContent(params.getContent());
            createdArticle.setDescription(params.getDescription());
            Long currentTime = System.currentTimeMillis();
            createdArticle.setCreateTime(currentTime);
            createdArticle.setUpdateTime(currentTime);
            articleRepository.save(createdArticle);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    public String updateArticle(ReqUpdateArticleDTO params) {
        try {
            Article updatedArticle = new Article();
            updatedArticle.setTitle(params.getTitle());
            updatedArticle.setContent(params.getContent());
            updatedArticle.setDescription(params.getDescription());
            updatedArticle.setId(params.getId());
            updatedArticle.setUpdateTime(System.currentTimeMillis());
            articleRepository.save(updatedArticle);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    public String deleteArticle(Long id) {
        try {
            articleRepository.deleteById(id);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }
}
