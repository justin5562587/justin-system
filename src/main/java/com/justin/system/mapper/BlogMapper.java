package com.justin.system.mapper;

import com.justin.system.models.Blog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BlogMapper {

    @Select("SELECT * FROM blog_table;")
    List<Blog> getBlogList();

    @Select("SELECT * FROM blog_table WHERE id=#{id};")
    Blog findBlogById(Long id);

    @Delete("DELETE FROM blog_table WHERE id=#{id};")
    void deleteBLogById(Long id);

    @Insert("INSERT INTO blog_table (title, content, description, img_url, label_name, create_time, update_time) " +
            "VALUES(#{title}, #{content}, #{description}, #{imgUrl}, #{labelName}, #{createTime}, #{updateTime});")
    void createBlog(Blog blog);
}
