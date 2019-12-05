package com.justin.system.service;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.request.ReqCreateBlogDTO;
import com.justin.system.entity.request.ReqUpdateBlogDTO;
import com.justin.system.entity.search.SearchBlogDTO;
import com.justin.system.mapper.BlogMapper;
import com.justin.system.models.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogMapper blogMapper;

    public ResponseWrapper getBlog(Long id) {
        Blog blog = blogMapper.getBlogById(id);
        return blog == null ?
                ResponseWrapper.fail("Blog Not Found") :
                ResponseWrapper.success(blog);
    }

    public ResponseWrapper getBlogList(SearchBlogDTO searchBlogDTO) {
        List<Blog> blogs = blogMapper.getBlogList(searchBlogDTO);
        return ResponseWrapper.success(blogs);
    }

    public ResponseWrapper createBlog(ReqCreateBlogDTO reqCreateBlogDTO) {
//        blogMapper.createBlog(reqCreateBlogDTO);
        return null;
    }

    public ResponseWrapper updateBlog(ReqUpdateBlogDTO reqUpdateBlogDTO) {
        return null;
    }

    public ResponseWrapper deleteBlog(Long id) {
        blogMapper.deleteBlog(id);
        return null;
    }
}
