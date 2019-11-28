package com.justin.system.models;

import lombok.Data;

@Data
public class Blog {

    private Long id;
    private Long createTime;
    private Long updateTime;
    private String title;
    private String content;
    private String description;
    private String imgUrl;
    private String labelName;
    private Long userId;

}
