package com.justin.system.models;

import lombok.Data;

@Data
public class Product {

    private Long id;
    private Long createTime;
    private Long updateTime;
    private String name;
    private String description;
    private String imgUrl;
    private Long price; // 实际价格
    private Long pointPrice; // 积分换购价格
    private int deleted; // 1删除 0为删除

}
