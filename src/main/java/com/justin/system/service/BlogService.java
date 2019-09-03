package com.justin.system.service;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.request.ReqCreateBlogDTO;
import com.justin.system.models.Blog;
import com.justin.system.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public ResponseWrapper getBlogList() {
        Iterable<Blog> foundBlogs = blogRepository.findAll();
        return ResponseWrapper.success(foundBlogs);
    }

    public ResponseWrapper getBlogDetail(Long id) {
        Optional<Blog> foundBlog = blogRepository.findById(id);
        return foundBlog.isPresent() ?
                ResponseWrapper.success(foundBlog) :
                ResponseWrapper.fail("error");
    }

    @Transactional
    public ResponseWrapper createBlog(ReqCreateBlogDTO params) {
        try {
            Blog newBlog = new Blog();
            newBlog.setTitle(params.getTitle());
            newBlog.setContent(params.getContent());
            newBlog.setDescription(params.getDescription());
            Long currentTime = System.currentTimeMillis();
            newBlog.setCreateTime(currentTime);
            newBlog.setUpdateTime(currentTime);
            newBlog.setLabelName(params.getLabelName());

            // need userId
            newBlog.setUserId(new Long(1));

            Blog savedBlog = blogRepository.save(newBlog);

            return ResponseWrapper.success(savedBlog);
        } catch (Exception e) {
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
