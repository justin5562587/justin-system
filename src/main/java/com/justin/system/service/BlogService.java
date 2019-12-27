package com.justin.system.service;

import com.justin.system.entity.basic.PageEntity;
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
import java.util.Map;

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
        List<Blog> blogList = blogMapper.getBlogList(searchBlogDTO);
        Map<String, Object> result = PageEntity.renderPageableRet(
                searchBlogDTO.getPageNumber(),
                searchBlogDTO.getPageSize(),
                blogList
        );
        return ResponseWrapper.success(result);
    }

    public ResponseWrapper createBlog(ReqCreateBlogDTO reqCreateBlogDTO, String token) {
        Blog blog = new Blog();

        blog.setCreateTime(System.currentTimeMillis());
        blog.setContent(reqCreateBlogDTO.getContent());
        blog.setTitle(reqCreateBlogDTO.getTitle());
        blog.setSubtitle(reqCreateBlogDTO.getSubtitle());
        blog.setLabel(reqCreateBlogDTO.getLabel());

        Long creatorId = JwtUtil.getClaim(SystemConstant.USER_ID, token).asLong();
        blog.setCreatorId(creatorId);

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
