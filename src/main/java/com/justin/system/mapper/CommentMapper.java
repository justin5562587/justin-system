package com.justin.system.mapper;

import com.justin.system.entity.search.SearchCommentDTO;
import com.justin.system.models.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CommentMapper {

    @Select("select * from comment_table where parent_id=#{parentId}")
    List<Comment> getCommentListByParentId(Long parentId);

    @Select("<script> "
            + "select * from comment_table where 1=1 "
            + "<if test='startTime != null'>"
            + "and create_time <![CDATA[ >= ]]> #{startTime} "
            + "</if>"
            + "<if test='endTime != null'>"
            + "and create_time <![CDATA[ <= ]]> #{endTime} "
            + "</if>"
            + "<if test='userId != null'>"
            + "and creator_id=#{userId} "
            + "</if>"
            + "<if test='parentId != null'>"
            + "and parent_id=#{parentId} "
            + "</if>"
            + "<if test='type != null'>"
            + "and type=#{type} "
            + "</if>"
            + "limit #{offset}, #{pageSize}"
            + " </script>")
    List<Comment> getCommentList(SearchCommentDTO searchCommentDTO);

    @Select("select * from comment_table where id=#{id}")
    @Results({
            @Result(property = "starCount", column = "star_count"),
            @Result(property = "createTime", column = "create_time"),
    })
    Comment getCommentById(Long id);

    @Insert("insert into comment_table(create_time, creator_id, refer_id, parent_id, content, type) " +
            "values(#{createTime}, #{creatorId}, #{referId}, #{parentId}, #{content}, #{type})")
    void save(Comment comment);

    @Update("update comment_table set deleted=1, delete_time=#{deleteTime} where id=#{id}")
    void deleteComment(@Param("id") Long id, @Param("deleteTime") Long deleteTime);

    @Update("update comment_table set star_count=#{starCount} where id=#{id}")
    void starComment(@Param("id") Long id, @Param("starCount") int starCount);
}
