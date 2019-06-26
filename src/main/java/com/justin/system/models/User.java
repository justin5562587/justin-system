package com.justin.system.models;

import com.justin.system.entity.enums.GroupEnum;

public class User {

    private Integer id;
    private Long createTime;
    private Long updateTime;
    private String username;
    private String password;
    private GroupEnum group;

    public User(String username, String password, Integer id, Long createTime, Long updateTime, GroupEnum group) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.group = group;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public GroupEnum getGroup() {
        return group;
    }

    public void setGroup(GroupEnum group) {
        this.group = group;
    }
}
