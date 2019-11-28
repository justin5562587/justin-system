package com.justin.system.entity.request;

import lombok.Data;

@Data
public class ReqUpdateArticleDTO {

    private Long id;
    private String title;
    private String description;
    private String content;

}
