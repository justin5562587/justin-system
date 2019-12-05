package com.justin.system.mapper;

import com.justin.system.entity.request.ReqCreateBlogDTO;
import com.justin.system.entity.request.ReqUpdateBlogDTO;
import com.justin.system.entity.search.SearchBlogDTO;
import com.justin.system.models.Blog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BlogMapper {

    @Insert("")
    int createBlog(ReqCreateBlogDTO reqCreateBlogDTO);

    @Update("")
    int updateBlog(ReqUpdateBlogDTO reqUpdateBlogDTO);

    @Select("select * from blog_table where id=#{id}")
    Blog getBlogById(Long id);

    @Select("")
    List<Blog> getBlogList(SearchBlogDTO searchBlogDTO);

    @Update("update from blog_table set deleted=1 where id=#{id}")
    int deleteBlog(Long id);


}
