package com.justin.system.controller;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.request.ReqCreateBlogDTO;
import com.justin.system.entity.request.ReqUpdateBlogDTO;
import com.justin.system.entity.search.SearchBlogDTO;
import com.justin.system.service.BlogService;
import org.apache.ibatis.annotations.Delete;
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
    public ResponseWrapper getBlogList(SearchBlogDTO searchBlogDTO) {
        System.out.println(searchBlogDTO);
        return blogService.getBlogList(searchBlogDTO);
    }

    @PostMapping("/create")
    public ResponseWrapper createBlog(
            @RequestHeader("Authorization") String token,
            @RequestBody ReqCreateBlogDTO reqCreateBlogDTO
    ) {
        return blogService.createBlog(reqCreateBlogDTO, token);
    }

    @PutMapping("/update")
    public ResponseWrapper updateBlog(@RequestBody ReqUpdateBlogDTO reqUpdateBlogDTO) {
        return blogService.updateBlog(reqUpdateBlogDTO);
    }

    @DeleteMapping("/")
    public ResponseWrapper deleteBlog(@RequestParam Long id) {
        return blogService.deleteBlog(id);
    }


}
