package com.justin.system.service;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.request.ReqCreateBlogDTO;
import com.justin.system.models.Blog;
import com.justin.system.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public ResponseWrapper getBlogList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        blogRepository.findAll(pageable);
        Page<Blog> pageBlogRet = blogRepository.findAll(pageable);
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("page", pageBlogRet.getNumber());
        retMap.put("size", pageBlogRet.getSize());
        retMap.put("total", pageBlogRet.getTotalElements());
        retMap.put("content", pageBlogRet.getContent());
        return ResponseWrapper.success(retMap);
    }

    public ResponseWrapper getBlogDetail(Long id) {
        Optional<Blog> foundBlog = blogRepository.findById(id);
        if (foundBlog.isPresent()) {
            return ResponseWrapper.success(foundBlog);
        }
        return ResponseWrapper.fail("Not Found");
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

            System.out.println(newBlog.toString());
            Blog addBlog = blogRepository.save(newBlog);

            return ResponseWrapper.success(addBlog);
        } catch (Exception e) {
            System.out.println(e.toString());
            return ResponseWrapper.fail(e);
        }
    }

    @Transactional
    public ResponseWrapper deleteBlog(Long id) {
        try {
            blogRepository.deleteById(id);
            return ResponseWrapper.success("delete blog successfully");
        } catch (Exception e) {
            return ResponseWrapper.fail(e);
        }
    }
}
