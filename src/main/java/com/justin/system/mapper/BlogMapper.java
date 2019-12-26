package com.justin.system.mapper;

import com.justin.system.entity.search.SearchBlogDTO;
import com.justin.system.models.Blog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BlogMapper {

    @Insert("insert into blog_table(create_time, title, subtitle, content, label, creator_id) " +
            "values(#{createTime}, #{title}, #{subtitle}, #{content}, #{label}, #{creatorId})")
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

    @Select("select * from blog_table where id=#{id} limit 1")
    Blog getBlogById(Long id);

    @Select("<script> " +
            "select * from blog_table where 1=1 "
            + "and deleted=0 "
            + "<if test='label != null'>"
            + "and label=#{label} "
            + "</if>"
            + "<if test='creatorId != null'>"
            + "and creator_id=#{creatorId} "
            + "</if>"
            + "<if test='startTime != null'>"
            + "and create_time <![CDATA[ >= ]]> #{startTime} "
            + "</if>"
            + "<if test='endTime != null'>"
            + "and create_time <![CDATA[ <= ]]> #{endTime} "
            + "</if>"
            + "limit #{offset} #{pageSize}"
            + "</script>")
    List<Blog> getBlogList(SearchBlogDTO searchBlogDTO);

    @Update("update blog_table set deleted=1 where id=#{id}")
    int deleteBlog(Long id);

}
