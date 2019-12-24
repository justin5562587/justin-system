package com.justin.system.entity.search;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class SearchCommentDTO {

    @Value()
    private int pageNumber;

    private int pageSize;

    // createTime时间的起始
    private Long startTime;
    private Long endTime;

    // 用于查找用户的评论
    private Long userId;

    // 用于作为评论的parentId查找子级评论
    private Long commentId;

    // 获取某种type下的评论
    private String type;

    public int getPageSize() {
        return pageSize == 0?20:pageSize;
    }
}
