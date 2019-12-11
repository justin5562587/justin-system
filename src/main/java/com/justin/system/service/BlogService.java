package com.justin.system.service;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.basic.SystemConstant;
import com.justin.system.entity.request.ReqCreateBlogDTO;
import com.justin.system.entity.request.ReqUpdateBlogDTO;
import com.justin.system.entity.search.SearchBlogDTO;
import com.justin.system.entity.utils.JwtUtil;
import com.justin.system.mapper.BlogMapper;
import com.justin.system.models.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogMapper blogMapper;

    private Long getUserIdFromToken(String token) {
        return JwtUtil.getClaim(token, SystemConstant.USER_ID).asLong();
    }

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

    public ResponseWrapper createBlog(ReqCreateBlogDTO reqCreateBlogDTO, String token) {
        Blog blog = new Blog();

        Long currentTime = System.currentTimeMillis();
        blog.setCreateTime(currentTime);
        blog.setUpdateTime(currentTime);

        blog.setContent(reqCreateBlogDTO.getContent());
        blog.setTitle(reqCreateBlogDTO.getTitle());
        blog.setSubtitle(reqCreateBlogDTO.getSubtitle());

        blog.setLabel(reqCreateBlogDTO.getLabel());

        Long userId = getUserIdFromToken(token);
        blog.setCreatorId(userId);

        blogMapper.createBlog(blog);
        return ResponseWrapper.success("Create Blog Successfully");
    }

    public ResponseWrapper updateBlog(ReqUpdateBlogDTO reqUpdateBlogDTO) {
        Blog blog = new Blog();
        blog.setId(reqUpdateBlogDTO.getId());

        blog.setUpdateTime(System.currentTimeMillis());
        blog.setTitle(reqUpdateBlogDTO.getTitle());
        blog.setSubtitle(reqUpdateBlogDTO.getSubtitle());
        blog.setContent(reqUpdateBlogDTO.getContent());
        blog.setLabel(reqUpdateBlogDTO.getLabel());

        blogMapper.updateBlog(blog);
        return ResponseWrapper.success("Update Blog Successfully");
    }

    public ResponseWrapper deleteBlog(Long id) {
        blogMapper.deleteBlog(id);
        return ResponseWrapper.success("Delete Blog Successfully");
    }
}
