package com.justin.system.entity.request;

import lombok.Data;

@Data
public class ReqUpdateProductDTO {

    private Long id;
    private String name;
    private String description;
    private String imgUrl;
    private Long price;
    private Long pointPrice;
    private int deleted;
}
