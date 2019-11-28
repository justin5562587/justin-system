package com.justin.system.service;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.request.ReqCreateBlogDTO;
import com.justin.system.models.Blog;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

    public ResponseWrapper getBlogList(int page, int size) {
        return ResponseWrapper.success(null);
    }

    public ResponseWrapper getBlogDetail(Long id) {
        return null;
    }

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

//            Blog addBlog = blogRepository.save(newBlog);

            return ResponseWrapper.success(null);
        } catch (Exception e) {
            return ResponseWrapper.error(e.toString());
        }
    }

    public ResponseWrapper deleteBlog(Long id) {
        return null;
    }
}
