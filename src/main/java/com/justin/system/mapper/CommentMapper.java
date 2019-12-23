package com.justin.system.mapper;

import com.justin.system.models.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CommentMapper {

    @Select("select * from comment_table where creator_id=#{userId}")
    List<Comment> getCommentListByUserId(Long userId);

    @Select("select * from comment_table where parent_id=#{parentId}")
    List<Comment> getCommentListByParentId(Long parentId);

    @Select("select * from comment_table where id=#{id}")
    Comment getCommentById(Long id);

    @Insert("insert into comment_table(create_time, creator_id, refer_id, parent_id, content, type}) " +
            "values(#{createTime}, #{creatorId}, #{parentId}, #{referId}, #{content}, #{type})")
    void save(Comment comment);

    @Update("update comment_table set deleted=1, delete_time=#{deleteTime} where id=#{id}")
    void deleteComment(Long id, Long deleteTime);

    @Update("update comment_table set star_count=#{starCount} where id=#{id}")
    void starComment(int starCount, Long id);
}
