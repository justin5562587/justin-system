package com.justin.system.entity.request;

import lombok.Data;

@Data
public class ReqCreateProductDTO {

    private Long id;
    private String name;
    private String imgUrl;
    private String description;
    private Long price;
    private Long pointPrice;
    private Long createTime;
    private Long updateTime;

}
