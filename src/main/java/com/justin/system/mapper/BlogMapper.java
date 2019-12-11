package com.justin.system.mapper;

import com.justin.system.entity.search.SearchBlogDTO;
import com.justin.system.models.Blog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BlogMapper {

    @Insert("insert into blog_table(create_time, update_time, title, subtitle, label, creator_id) " +
            "values(#{createTime}, #{updateTime}, #{title}, #{subtitle}, #{label}, #{creatorId})")
    int createBlog(Blog blog);

    @Update("<script> " +
            "update product_table set "
            + "<if test='title != null'>"
            + "title = #{title} "
            + "</if>"
            + "<if test='subtitle != null'>"
            + ",subtitle = #{subtitle} "
            + "</if>"
            + "<if test='content != null'>"
            + ",content = #{content} "
            + "</if>"
            + "<if test='updateTime != null'>"
            + ",update_time = #{updateTime} "
            + "</if>"
            + " where id = #{id}"
            + "</script>")
    int updateBlog(Blog blog);

    @Select("select * from blog_table where id=#{id}")
    Blog getBlogById(Long id);

    @Select("select * from blog_table where")
    List<Blog> getBlogList(SearchBlogDTO searchBlogDTO);

    @Update("update from blog_table set deleted=1 where id=#{id}")
    int deleteBlog(Long id);


}
