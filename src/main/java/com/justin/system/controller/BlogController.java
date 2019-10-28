package com.justin.system.controller;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.basic.SystemConstant;
import com.justin.system.entity.request.ReqCreateBlogDTO;
import com.justin.system.entity.utils.JwtUtil;
import com.justin.system.models.User;
import com.justin.system.repository.UserRepository;
import com.justin.system.service.BlogService;
import com.justin.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list")
    public ResponseWrapper getBlogList(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return blogService.getBlogList(page, size);
    }

    @GetMapping("/")
    public ResponseWrapper getBlogDetail(@RequestHeader("Authorization") String authorization, @RequestParam Long id) {
        String token = authorization;
        String email = JwtUtil.getClaim(token, SystemConstant.USER_EMAIL);
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            JwtUtil.validateToken(token, optionalUser.get());
            return blogService.getBlogDetail(id);
        }
        return ResponseWrapper.fail("Need Authorization");
    }

    @PostMapping("/")
    public ResponseWrapper createBlog(@RequestBody ReqCreateBlogDTO params) {
        return blogService.createBlog(params);
    }

    @DeleteMapping("/")
    public ResponseWrapper deleteBlog(@RequestParam Long id) {
        return blogService.deleteBlog(id);
    }

}
