package com.justin.system.entity.search;

import lombok.Data;

@Data
public class SearchCommentDTO {

    private int pageNumber = 0;

    private int pageSize = 20;

    private int offset = pageSize * pageNumber;

    // createTime时间的起始
    private Long startTime;
    private Long endTime;

    // 用于查找用户的评论
    private Long userId;

    // 用于作为评论的parentId查找子级评论
    private Long parentId;

    private Long referId;
    private String referType;
}
