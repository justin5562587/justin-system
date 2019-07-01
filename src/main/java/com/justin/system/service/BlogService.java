package com.justin.system.service;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.request.ReqCreateBlogDTO;
import com.justin.system.entity.request.ReqUpdateBlogDTO;
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
        return ResponseWrapper.successRender(foundBlogs);
    }

    public ResponseWrapper getBlogDetail(Long id) {
        Optional<Blog> foundBlog = blogRepository.findById(id);
        return foundBlog.isPresent() ?
                ResponseWrapper.successRender(foundBlog) :
                ResponseWrapper.failRender("error");
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

            return ResponseWrapper.successRender(savedBlog);
        } catch (Exception e) {
            return ResponseWrapper.failRender(e);
        }
    }

    @Transactional
    public ResponseWrapper updateBlog(ReqUpdateBlogDTO params) {
        System.out.println(params);
        return ResponseWrapper.successRender("update blog successfully");
    }

    @Transactional
    public ResponseWrapper deleteBlog(Long id) {
        try {
            blogRepository.deleteById(id);
            return ResponseWrapper.successRender("delete blog successfully");
        } catch (Exception e) {
            return ResponseWrapper.failRender(e);
        }
    }
}
