package com.justin.system.entity.request;

import lombok.Data;

@Data
public class ReqCreateBlogDTO {

    private String title;
    private String content;
    private String description;
    private String imgUrl;
    private String labelName;

}
