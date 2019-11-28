package com.justin.system.service;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.request.ReqCreateArticleDTO;
import com.justin.system.entity.request.ReqUpdateArticleDTO;
import com.justin.system.models.Article;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    public ResponseWrapper getArticleList(int page, int size) {
        return ResponseWrapper.success(null);
    }

    public ResponseWrapper getArticleDetail(Long id) {
        return ResponseWrapper.success(id);
    }

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

//            articleRepository.save(article);

            return ResponseWrapper.success("successfully create article");
        } catch (Exception e) {
            return ResponseWrapper.fail(e.getMessage());
        }
    }

    public ResponseWrapper updateArticle(ReqUpdateArticleDTO params) {
        return ResponseWrapper.success(null);
    }

    public ResponseWrapper deleteArticle(Long id) {
        return ResponseWrapper.success(id);
    }
}
