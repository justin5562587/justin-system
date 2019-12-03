package com.justin.system.models;

import lombok.Data;

@Data
public class ProductOrder {

    private Long id;
    private Long createTime;
    private Long updateTime;
    private String status;
    private Long buyerId;
    private Long handlerId;
    private Long[] productIds;

}
