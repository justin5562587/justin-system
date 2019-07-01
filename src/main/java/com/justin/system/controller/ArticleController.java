package com.justin.system.controller;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.request.ReqCreateArticleDTO;
import com.justin.system.entity.request.ReqUpdateArticleDTO;
import com.justin.system.models.Article;
import com.justin.system.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/list")
    public ResponseWrapper getArticleList() {
        return articleService.getArticleList();
    }

    @GetMapping("/detail")
    public ResponseWrapper getArticle(@RequestParam Long id) {
        return articleService.getArticleDetail(id);
    }

    @PostMapping("/")
    public ResponseWrapper createArticle(@RequestBody ReqCreateArticleDTO params) {
        return articleService.createArticle(params);
    }

    @PutMapping("/")
    public ResponseWrapper updateArticle(@RequestBody ReqUpdateArticleDTO params) {
        return articleService.updateArticle(params);
    }

    @DeleteMapping("/")
    public ResponseWrapper deleteArticle(@RequestParam Long id) {
        return articleService.deleteArticle(id);
    }

}
