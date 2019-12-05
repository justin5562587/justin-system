package com.justin.system.entity.request;

import lombok.Data;

@Data
public class ReqUpdateBlogDTO {

    private Long creatorId;
    private Long updateTime;
    private String title;
    private String subtitle;
    private String content;
    private String label;
}
