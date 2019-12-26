package com.justin.system.entity.search;

import lombok.Data;

@Data
public class SearchPageDTO {

    private int pageSize = 20;
    private int pageNumber = 0;
    private int offset = pageSize * pageNumber;
}
