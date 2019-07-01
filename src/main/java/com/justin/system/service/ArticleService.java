package com.justin.system.service;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.request.ReqCreateArticleDTO;
import com.justin.system.entity.request.ReqUpdateArticleDTO;
import com.justin.system.models.Article;
import com.justin.system.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public ResponseWrapper getArticleList() {
        Iterable<Article> foundArticles = articleRepository.findAll();

        return ResponseWrapper.successRender(foundArticles);
    }

    public ResponseWrapper getArticleDetail(Long id) {
        Optional<Article> foundArticle = articleRepository.findById(id);
        return foundArticle.isPresent() ?
                ResponseWrapper.successRender(foundArticle) :
                ResponseWrapper.failRender("error");
    }

    @Transactional
    public ResponseWrapper createArticle(ReqCreateArticleDTO params) {
        try {
            Article createdArticle = new Article();
            createdArticle.setTitle(params.getTitle());
            createdArticle.setContent(params.getContent());
            createdArticle.setDescription(params.getDescription());
            Long currentTime = System.currentTimeMillis();
            createdArticle.setCreateTime(currentTime);
            createdArticle.setUpdateTime(currentTime);
            articleRepository.save(createdArticle);

            return ResponseWrapper.successRender("successfully create article");
        } catch (Exception e) {
            return ResponseWrapper.failRender("create article fail");
        }
    }

    @Transactional
    public ResponseWrapper updateArticle(ReqUpdateArticleDTO params) {
        try {
            Article updatedArticle = new Article();
            updatedArticle.setTitle(params.getTitle());
            updatedArticle.setContent(params.getContent());
            updatedArticle.setDescription(params.getDescription());
            updatedArticle.setId(params.getId());
            updatedArticle.setUpdateTime(System.currentTimeMillis());
            articleRepository.save(updatedArticle);

            return ResponseWrapper.successRender("successfully update article");
        } catch (Exception e) {
            return ResponseWrapper.failRender("update article fail");
        }
    }

    @Transactional
    public ResponseWrapper deleteArticle(Long id) {
        try {
            articleRepository.deleteById(id);

            return ResponseWrapper.successRender("delete article successfully");
        } catch (Exception e) {
            return ResponseWrapper.failRender("delete article fail");
        }
    }
}
