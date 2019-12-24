package com.justin.system.service;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.basic.SystemConstant;
import com.justin.system.entity.request.ReqCreateCommentDTO;
import com.justin.system.entity.search.SearchCommentDTO;
import com.justin.system.entity.utils.JwtUtil;
import com.justin.system.mapper.CommentMapper;
import com.justin.system.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    // 根据token获取userId
    private Long getUserIdFromToken(String token) {
        return JwtUtil.getClaim(token, SystemConstant.USER_ID).asLong();
    }

    // 过滤已删除的评论 + 设置hasChild
    private List<Comment> filterCommentList(List<Comment> commentList) {
        List<Comment> result = new ArrayList<>();
        for (Comment c : commentList) {
            if (c.getDeleted() == 0) {
                result.add(c);
            }
        }
        for (Comment rc : result) {
            List<Comment> childCommentList = commentMapper.getCommentListByParentId(rc.getId());
            rc.setHasChild(!childCommentList.isEmpty());
        }
        return result;
    }

    public ResponseWrapper getCommentList(SearchCommentDTO searchCommentDTO) {
        // 优先根据userId进行筛选
        Long userId = searchCommentDTO.getUserId();
        if (userId != null) {
            List<Comment> commentList = commentMapper.getCommentListByUserId(userId);
            List<Comment> result = filterCommentList(commentList);
            return ResponseWrapper.success(result);
        }

        // 没有userId根据commentId筛选
        Long commentId = searchCommentDTO.getCommentId();
        if (commentId != null) {
            List<Comment> commentList = commentMapper.getCommentListByParentId(commentId);
            List<Comment> result = filterCommentList(commentList);
            return ResponseWrapper.success(result);
        }

        return ResponseWrapper.fail("Need Search Params");
    }

    public ResponseWrapper createComment(ReqCreateCommentDTO reqCreateCommentDTO, String token) {
        Comment comment = new Comment();

        comment.setContent(reqCreateCommentDTO.getContent());

        // 当前只开放在Blog下评论
        comment.setType("BLOG");
        comment.setReferId(reqCreateCommentDTO.getReferId());

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

            int finalStarCount = isCancel == 0 ? oldStarCount + 1 : oldStarCount - 1;
            commentMapper.starComment(id, finalStarCount);

            return ResponseWrapper.success(isCancel == 0 ? "Star Comment Successfully" : "Cancel Star Comment Successfully");
        }
        return ResponseWrapper.fail("Comment Can Not Found");
    }

    public ResponseWrapper deleteComment(Long id) {
        commentMapper.deleteComment(id, System.currentTimeMillis());
        return ResponseWrapper.success("Delete Comment Successfully");
    }
}
