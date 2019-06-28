package com.justin.system.controller;

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
    public ArrayList<Article> getArticleList() {
        return articleService.getArticleList();
    }

    @GetMapping("/detail")
    public Article getArticle(@RequestParam Long id) {
        return articleService.getArticleDetail(id);
    }

    @PostMapping("/")
    public String createArticle(@RequestBody ReqCreateArticleDTO params) {
        return articleService.createArticle(params);
    }

    @PutMapping("/")
    public String updateArticle(@RequestBody ReqUpdateArticleDTO params) {
        return articleService.updateArticle(params);
    }

    @DeleteMapping("/")
    public String deleteArticle(@RequestParam Long id) {
        return articleService.deleteArticle(id);
    }

}
