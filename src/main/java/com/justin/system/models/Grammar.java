package com.justin.system.models;

public class Grammar {

    private Long id;
    private Long createTime;
    private Long updateTime;
    private String title;
    private String description;
    private String[] usageList;
    private String category;

    public Grammar(Long id, Long createTime, Long updateTime, String title, String description, String[] usageList, String category) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.title = title;
        this.description = description;
        this.usageList = usageList;
        this.category = category;
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

    public String[] getUsageList() {
        return usageList;
    }

    public void setUsageList(String[] usageList) {
        this.usageList = usageList;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
