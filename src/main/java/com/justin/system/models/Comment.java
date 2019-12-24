package com.justin.system.models;

import lombok.Data;

import java.util.List;

@Data
public class Comment {

    private Long id;
    private Long creatorId;
    private Long createTime;
    private Long deleteTime;
    // 关联id 是Type类型的id为referId的评论
    private Long referId;
    // type 可以是 BLOG
    private String type;
    private Long parentId;
    private int starCount;
    private int deleted;
    private String content;
    private boolean hasChild;
    private List<Comment> childComments;
}
