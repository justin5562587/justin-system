package com.justin.system.controller;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.request.ReqCreateBlogDTO;
import com.justin.system.entity.request.ReqUpdateBlogDTO;
import com.justin.system.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/list")
    public ResponseWrapper getBlogList() {
        return blogService.getBlogList();
    }

    @GetMapping("/detail")
    public ResponseWrapper getBlogDetail(@RequestParam Long id) {
        return blogService.getBlogDetail(id);
    }

    @PostMapping("/")
    public ResponseWrapper createBlog(@RequestBody ReqCreateBlogDTO params) {
        return blogService.createBlog(params);
    }

    @PutMapping("/")
    public ResponseWrapper updateBlog(@RequestBody ReqUpdateBlogDTO params) {
        return blogService.updateBlog(params);
    }

    @DeleteMapping("/")
    public ResponseWrapper deleteBlog(@RequestParam Long id) {
        return blogService.deleteBlog(id);
    }

}
