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
    public ResponseWrapper getCommentListForUser(@RequestParam Long id) {
        return commentService.getCommentList(id);
    }

    @PostMapping("/create")
    public ResponseWrapper createComment(@RequestBody ReqCreateCommentDTO reqCreateCommentDTO) {
        return commentService.createComment(reqCreateCommentDTO);
    }

    @PostMapping("/star")
    public ResponseWrapper starComment(Long id) {
        return commentService.starComment(id);
    }

    @DeleteMapping("/delete")
    public ResponseWrapper deleteComment(Long id) {
        return commentService.deleteComment(id);
    }


}
