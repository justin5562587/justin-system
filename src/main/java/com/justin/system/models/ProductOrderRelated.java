package com.justin.system.models;

import lombok.Data;

@Data
public class ProductOrderRelated {

    private Long id;
    private Long productOrderId;
    private Long productId;
}
