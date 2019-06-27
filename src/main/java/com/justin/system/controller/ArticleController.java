package com.justin.system.controller;

import com.justin.system.entity.request.ReqCreateArticleDTO;
import com.justin.system.entity.request.ReqUpdateArticleDTO;
import com.justin.system.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/list")
    public String getArticleList() {
        return articleService.getArticleList();
    }

    @GetMapping("/detail")
    public String getArticle(@RequestParam Integer id) {
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
    public String deleteArticle(@RequestParam Integer id) {
        return articleService.deleteArticle(id);
    }

}
