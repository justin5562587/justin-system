package com.justin.system.models;



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

    public Product(Long id, Long createTime, Long updateTime, String name, String description, String imgUrl, Long price, Long pointPrice) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.price = price;
        this.pointPrice = pointPrice;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getPointPrice() {
        return pointPrice;
    }

    public void setPointPrice(Long pointPrice) {
        this.pointPrice = pointPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", price=" + price +
                ", pointPrice=" + pointPrice +
                '}';
    }
}
