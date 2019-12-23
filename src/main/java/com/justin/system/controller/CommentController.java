package com.justin.system.controller;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.request.ReqCreateCommentDTO;
import com.justin.system.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/list")
    public ResponseWrapper getUserCommentList(@RequestParam Long userId) {
        return commentService.getUserCommentList(userId);
    }

    @PostMapping("/create")
    public ResponseWrapper createComment(
            @RequestHeader("Authorization") String token,
            @RequestBody ReqCreateCommentDTO reqCreateCommentDTO
    ) {
        return commentService.createComment(reqCreateCommentDTO, token);
    }

    @PostMapping("/star")
    public ResponseWrapper starComment(Long id, int isCancel) {
        return commentService.starComment(id, isCancel);
    }

    @DeleteMapping("/delete")
    public ResponseWrapper deleteComment(Long id) {
        return commentService.deleteComment(id);
    }


}
