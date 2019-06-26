package com.justin.system.controller;

import com.justin.system.models.Article;
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
    public String createArticle(@RequestBody Article article) {
        return articleService.createArticle(article);
    }

    @PutMapping("/")
    public String updateArticle(@RequestBody Article article) {
        return articleService.updateArticle(article);
    }

    @DeleteMapping("/")
    public String deleteArticle(@RequestParam Integer id) {
        return articleService.deteleArticle(id);
    }

}
