package com.justin.system.controller;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.request.ReqCreateBlogDTO;
import com.justin.system.entity.request.ReqUpdateBlogDTO;
import com.justin.system.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    public ResponseWrapper getBlog(@RequestParam Long id) {
        return blogService.getBlog(id);
    }

    @GetMapping("/list")
    public ResponseWrapper getBlogList(
            @Request
    ) {
        return blogService.getBlogList();
    }

    @PostMapping("/create")
    public ResponseWrapper createBlog(@RequestBody ReqCreateBlogDTO reqCreateBlogDTO) {
        return blogService.createBlog(reqCreateBlogDTO);
    }

    @PutMapping("/update")
    public ResponseWrapper updateBlog(@RequestBody ReqUpdateBlogDTO reqUpdateBlogDTO) {
        return blogService.updateBlog(reqUpdateBlogDTO);
    }

    @PostMapping("/")
    public 


}
