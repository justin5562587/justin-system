package com.justin.system.entity.request;

import lombok.Data;

@Data
public class ReqCreateArticleDTO {

    private String title;
    private String description;
    private String content;
    private String label;

}
