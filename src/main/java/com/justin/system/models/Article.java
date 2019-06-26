package com.justin.system.models;

public class Article {

    private Integer id;
    private Long createTime;
    private Long updateTime;
    private String title;
    private String description;
    private String content;
    private User author;

    public Article(Integer id, Long createTime, Long updateTime, String title, String description, String content) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.title = title;
        this.description = description;
        this.content = content;
    }

    public Article(Integer id, Long createTime, Long updateTime, String title, String description, String content, User author) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.title = title;
        this.description = description;
        this.content = content;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
