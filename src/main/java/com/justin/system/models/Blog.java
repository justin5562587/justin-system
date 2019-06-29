package com.justin.system.models;

import javax.persistence.*;

@Entity
@Table(name = "blog_table")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long createTime;
    private Long updateTime;
    private String title;
    private String content;
    private String description;
    private String imgUrl;
    private User author;
    private String labelType;

    public Blog() {
    }

    public Blog(Long createTime, Long updateTime, String title, String content, String description, String imgUrl, User author, String labelType) {
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.title = title;
        this.content = content;
        this.description = description;
        this.imgUrl = imgUrl;
        this.author = author;
        this.labelType = labelType;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getLabelType() {
        return labelType;
    }

    public void setLabelType(String labelType) {
        this.labelType = labelType;
    }
}
