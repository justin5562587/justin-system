package com.justin.system.entity.search;

import lombok.Data;

@Data
public class SearchCommentDTO {

    // 用于查找用户的评论
    private Long userId;

    // 用于作为评论的parentId查找子级评论
    private Long commentId;
}
