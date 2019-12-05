package com.justin.system.entity.request;

import lombok.Data;

@Data
public class ReqCreateBlogDTO {

    private Long createTime;
    private Long updateTime;
    private Long creatorId;
    private String title;
    private String subtitle;
    private String content;
    private String label;

}
