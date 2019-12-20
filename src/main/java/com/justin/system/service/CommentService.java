package com.justin.system.service;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.request.ReqCreateCommentDTO;
import com.justin.system.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public ResponseWrapper getCommentList(Long id) {
        commentMapper.getCommentListByUserId(id);
        return null;
    }

    public ResponseWrapper createComment(ReqCreateCommentDTO reqCreateCommentDTO) {
        return null;
    }

    public ResponseWrapper deleteComment(Long id) {
        return null;
    }

    public ResponseWrapper starComment(Long id) {
        return null;
    }
}
