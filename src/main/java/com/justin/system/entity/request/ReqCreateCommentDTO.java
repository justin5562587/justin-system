package com.justin.system.entity.request;

import lombok.Data;

@Data
public class ReqCreateCommentDTO {

    private Long createTime;
    private Long deleteTime;
    private Long parentId;
    private int starCount;
    private int deleted;
    private String type;
    private Long referId;
    private String content;
}
