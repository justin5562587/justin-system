package com.justin.system.entity.request;

import lombok.Data;

@Data
public class ReqCreateCommentDTO {

    private String referType;
    private Long referId;
    private Long parentId;
    private String content;
}
