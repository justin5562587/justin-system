package com.justin.system.entity.request;

public class ReqCreateBlogDTO {

    private String title;
    private String content;
    private String description;
    private String imgUrl;
    private String labelTypeString;

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

    public String getLabelTypeString() {
        return labelTypeString;
    }

    public void setLabelTypeString(String labelTypeString) {
        this.labelTypeString = labelTypeString;
    }
}
