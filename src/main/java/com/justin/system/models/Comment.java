package com.justin.system.models;

import lombok.Data;

@Data
public class Comment {

    private Long id;
    private Long creatorId;
    private Long createTime;
    private Long deleteTime;
    private Long parentId;
    private int starCount;
    private int deleted;
    private String type;
    private String content;
}
