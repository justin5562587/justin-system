package com.justin.system.models;

import lombok.Data;

@Data
public class Blog {

    private Long id;
    private Long creatorId;
    private Long createTime;
    private Long updateTime;
    private String title;
    private String subtitle;
    private String content;
    private String label;

}
