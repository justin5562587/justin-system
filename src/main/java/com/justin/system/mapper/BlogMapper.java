package com.justin.system.mapper;

import com.justin.system.models.Blog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BlogMapper {

    @Select("SELECT * FROM blog_table;")
    List<Blog> getBlogList();

//    @Update()
//    void updateBlog();
//
//    @Delete()
//    void deleteBlog();
//
//    @Insert()
//    Blog insertBlog();
}
