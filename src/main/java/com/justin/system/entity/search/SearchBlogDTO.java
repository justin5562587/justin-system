package com.justin.system.entity.search;

import lombok.Data;

@Data
public class SearchBlogDTO {

    private int pageSize = 20;
    private int pageNumber = 0;
    private int offset = pageNumber * pageSize;

    private String label;

    private Long startTime;
    private Long endTime;

    private Long creatorId;

}
