package com.justin.system.service;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.basic.SystemConstant;
import com.justin.system.entity.request.ReqCreateCommentDTO;
import com.justin.system.entity.utils.JwtUtil;
import com.justin.system.mapper.CommentMapper;
import com.justin.system.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    // 根据token获取userId
    private Long getUserIdFromToken(String token) {
        return JwtUtil.getClaim(token, SystemConstant.USER_ID).asLong();
    }

    // 根据评论列表 递归获取所有子评论
    private List<Comment> getCommentChildList(List<Comment> commentList) {
        for (Comment c : commentList) {
            List<Comment> childCommentList = commentMapper.getCommentListByParentId(c.getId());
            if (!childCommentList.isEmpty()) {
                List<Comment> ret = getCommentChildList(childCommentList);
                c.setChildComments(ret);
            }
        }

        return commentList;
    }

    public ResponseWrapper getUserCommentList(Long userId) {
        List<Comment> commentList = commentMapper.getCommentListByUserId(userId);
        List<Comment> result = getCommentChildList(commentList);
        return ResponseWrapper.success(result);
    }

    public ResponseWrapper createComment(ReqCreateCommentDTO reqCreateCommentDTO, String token) {
        Comment comment = new Comment();

        comment.setContent(reqCreateCommentDTO.getContent());
        comment.setType(reqCreateCommentDTO.getType());
        comment.setCreateTime(System.currentTimeMillis());

        Long userId = getUserIdFromToken(token);
        comment.setCreatorId(userId);

        Long parentId = reqCreateCommentDTO.getParentId();
        if (parentId != null) {
            comment.setParentId(parentId);
        }

        commentMapper.save(comment);
        return ResponseWrapper.success(comment);
    }

    public ResponseWrapper starComment(Long id, int isCancel) {
        Comment comment = commentMapper.getCommentById(id);
        if (comment != null) {
            int oldStarCount = comment.getStarCount();

            int finalStarCount = isCancel != 0 ? oldStarCount + 1 : oldStarCount - 1;
            commentMapper.starComment(finalStarCount, id);

            String message = isCancel != 0 ? "Star Comment Successfully" : "Cancel Star Comment Successfully";
            return ResponseWrapper.success(message);
        }
        return ResponseWrapper.fail("Comment Can Not Found");
    }

    public ResponseWrapper deleteComment(Long id) {
        commentMapper.deleteComment(id, System.currentTimeMillis());
        return ResponseWrapper.success("Delete Comment Successfully");
    }
}
