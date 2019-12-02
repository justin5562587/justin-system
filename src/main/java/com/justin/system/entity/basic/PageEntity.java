package com.justin.system.entity.basic;

import java.util.HashMap;
import java.util.Map;

public class PageEntity {

    static public Map<String, Object> renderPageableRet(int pageNumber, int pageSize, Object content) {
        Map<String, Object> result = new HashMap<>();
        result.put("pageNumber", pageNumber);
        result.put("pageSize", pageSize);
        result.put("content", content);
        return result;
    }
}
