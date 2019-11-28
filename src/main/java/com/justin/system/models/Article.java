package com.justin.system.models;

import lombok.Data;

@Data
public class Article {

    private Long id;
    private Long createTime;
    private Long updateTime;
    private String title;
    private String description;
    private String content;
    private String label;
    private Integer readNum;
    private Integer starNum;

}
