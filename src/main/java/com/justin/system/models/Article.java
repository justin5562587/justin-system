package com.justin.system.models;

import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Entity
@Table(name = "article_table")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long createTime;
    @LastModifiedDate
    private Long updateTime;
    private String title;
    private String description;
    private String content;

    public Article() {
    }

    public Article(Long id, Long createTime, Long updateTime, String title, String description, String content) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.title = title;
        this.description = description;
        this.content = content;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
