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
        System.out.println(searchCommentDTO.toString());
        List<Comment> commentList = commentMapper.getCommentList(searchCommentDTO);
        System.out.println(commentList);
        List<Comment> result = filterCommentList(commentList);
        return ResponseWrapper.success(result);
    }

    public ResponseWrapper createComment(ReqCreateCommentDTO reqCreateCommentDTO, String token) {
        Comment comment = new Comment();

        comment.setContent(reqCreateCommentDTO.getContent());
        comment.setReferType(reqCreateCommentDTO.getReferType());
        comment.setReferId(reqCreateCommentDTO.getReferId());
        comment.setCreateTime(System.currentTimeMillis());

        Long userId = JwtUtil.getClaim(SystemConstant.USER_ID, token).asLong();
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
