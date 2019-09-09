package com.justin.system.service;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.request.ReqCreateBlogDTO;
import com.justin.system.mapper.BlogMapper;
import com.justin.system.models.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BlogService {

    @Autowired
    private BlogMapper blogMapper;

    public ResponseWrapper getBlogList() {
        return ResponseWrapper.success(blogMapper.getBlogList());
    }

    public ResponseWrapper getBlogDetail(Long id) {
        Blog foundBlog = blogMapper.findBlogById(id);
        if (foundBlog == null) {
            return ResponseWrapper.fail("Not Found");
        }
        return ResponseWrapper.success(foundBlog);
    }

    @Transactional
    public ResponseWrapper createBlog(ReqCreateBlogDTO params) {
        try {
            Blog newBlog = new Blog();
            newBlog.setTitle(params.getTitle());
            newBlog.setContent(params.getContent());
            newBlog.setDescription(params.getDescription());
            newBlog.setLabelName(params.getLabelName());
            newBlog.setImgUrl(params.getImgUrl());

            // handle time
            Long currentTime = System.currentTimeMillis();
            newBlog.setCreateTime(currentTime);
            newBlog.setUpdateTime(currentTime);

            Blog savedBlog = blogMapper.createBlog(newBlog);

            return ResponseWrapper.success(savedBlog);
        } catch (Exception e) {
            return ResponseWrapper.fail(e);
        }
    }

    @Transactional
    public ResponseWrapper deleteBlog(Long id) {
        try {
            blogMapper.deleteBLogById(id);
            return ResponseWrapper.success("delete blog successfully");
        } catch (Exception e) {
            return ResponseWrapper.fail(e);
        }
    }
}
