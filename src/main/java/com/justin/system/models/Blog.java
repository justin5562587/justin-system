package com.justin.system.models;

import javax.persistence.*;

@Entity
@Table(name = "blog_table")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT NOT NULL AUTO_INCREMENT")
    private Long id;
    private Long createTime;
    private Long updateTime;
    private String title;
    private String content;
    private String description;
    private String imgUrl;
    private String labelName;
    private Long userId;

    public Blog() {
    }

    public Blog(Long createTime, Long updateTime, String title, String content, String description, String imgUrl, String labelName, Long userId) {
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.title = title;
        this.content = content;
        this.description = description;
        this.imgUrl = imgUrl;
        this.labelName = labelName;
        this.userId = userId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
