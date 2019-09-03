package com.justin.system.service;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.enums.ErrorTypeEnum;
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
        return ResponseWrapper.success(foundArticles);
    }

    public ResponseWrapper getArticleDetail(Long id) {
        Optional<Article> foundArticle = articleRepository.findById(id);
        return foundArticle.isPresent() ?
                ResponseWrapper.success(foundArticle) :
                ResponseWrapper.fail(ErrorTypeEnum.CAN_NOT_FOUND.getDescription());
    }

    @Transactional
    public ResponseWrapper createArticle(ReqCreateArticleDTO params) {
        try {
            Article article = new Article();
            article.setTitle(params.getTitle());
            article.setContent(params.getContent());
            article.setDescription(params.getDescription());
            String label = params.getLabel();
            if (label != null) {
                article.setLabel(params.getLabel());
            }
            Long currentTime = System.currentTimeMillis();
            article.setCreateTime(currentTime);
            article.setUpdateTime(currentTime);

            System.out.println(article.getClass().getName());

//            articleRepository.save(article);

            return ResponseWrapper.success("successfully create article");
        } catch (Exception e) {
            return ResponseWrapper.fail(ErrorTypeEnum.CREATE_FAILURE.getDescription());
        }
    }

    @Transactional
    public ResponseWrapper updateArticle(ReqUpdateArticleDTO params) {
        try {
            Optional<Article> optionalArticle = articleRepository.findById(params.getId());
            if (optionalArticle.isPresent()) {
                Article article = optionalArticle.get();
                article.setTitle(params.getTitle());
                article.setDescription(params.getDescription());
                article.setContent(params.getContent());

                articleRepository.save(article);
                return ResponseWrapper.success(article);
            } else {
                return ResponseWrapper.fail(ErrorTypeEnum.CAN_NOT_FOUND.getDescription());
            }
        } catch (Exception e) {
            return ResponseWrapper.fail(ErrorTypeEnum.UPDATE_FAILURE.getDescription());
        }
    }

    @Transactional
    public ResponseWrapper deleteArticle(Long id) {
        try {
            articleRepository.deleteById(id);

            return ResponseWrapper.success("delete article successfully");
        } catch (IllegalArgumentException e) {
            return ResponseWrapper.fail(ErrorTypeEnum.DELETE_FAILURE.getDescription());
        }
    }
}
