package com.justin.system.mapper;

import com.justin.system.models.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CommentMapper {

    @Select("select * from comment_table where creator_id=#{}")
    List<Comment> getCommentListByUserId(Long id);

    @Insert("insert into comment_table(create_time, creator_id, parent_id, content, type, star_count}) " +
            "values(#{createTime}, #{creatorId}, #{parentId}, #{content}, #{type}, 0})")
    void save(Comment comment);

    @Update("update comment_table set deleted=1 where id=#{id}")
    void deleteComment(Long id);

    @Update("update commnet_table set star_count=#{} where id=#{id}")
    void starComment(Long id);
}
