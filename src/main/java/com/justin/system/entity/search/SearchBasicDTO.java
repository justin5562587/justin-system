package com.justin.system.entity.search;

import lombok.Data;

@Data
public class SearchBasicDTO {

    // 默认20
    private int pageSize;

    // 首页为0
    private int pageNumber;

    // createTime的起始时间
    private long startTime;
    private long endTime;

}
