package com.justin.system.models;

import java.util.Arrays;

public class ProductOrder {

    private Long id;
    private Long createTime;
    private Long updateTime;
    private String status;
    private Integer orderPrice;
    private Long buyerId;
    private Long handlerId;
    private Long[] productIds;

    public ProductOrder(Long id, Long createTime, Long updateTime, String status, Integer orderPrice, Long buyerId, Long handlerId, Long[] productIds) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
        this.orderPrice = orderPrice;
        this.buyerId = buyerId;
        this.handlerId = handlerId;
        this.productIds = productIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Long handlerId) {
        this.handlerId = handlerId;
    }

    public Long[] getProductIds() {
        return productIds;
    }

    public void setProductIds(Long[] productIds) {
        this.productIds = productIds;
    }

    @Override
    public String toString() {
        return "ProductOrder{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status='" + status + '\'' +
                ", orderPrice=" + orderPrice +
                ", buyerId=" + buyerId +
                ", handlerId=" + handlerId +
                ", productIds=" + Arrays.toString(productIds) +
                '}';
    }
}
