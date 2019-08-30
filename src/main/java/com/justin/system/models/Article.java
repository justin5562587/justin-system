package com.justin.system.models;

import com.justin.system.entity.enums.LabelTypeEnum;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Entity
@Table(name = "article_table")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT NOT NULL AUTO_INCREMENT")
    private Long id;
    private Long createTime;
    @LastModifiedDate
    private Long updateTime;
    private String title;
    private String description;
    private String content;
    private String label;
    private Integer readNum;
    private Integer starNum;

    public Article() {
        this.starNum = 0;
        this.readNum = 0;
        this.label = LabelTypeEnum.OTHER.toString();
    }

    public Article(Long id, Long createTime, Long updateTime, String title, String description, String content, String label) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.title = title;
        this.description = description;
        this.content = content;
        this.label = label;
        this.starNum = 0;
        this.readNum = 0;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public Integer getStarNum() {
        return starNum;
    }

    public void setStarNum(Integer starNum) {
        this.starNum = starNum;
    }
}
