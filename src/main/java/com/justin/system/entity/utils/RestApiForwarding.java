package com.justin.system.entity.utils;

import com.justin.system.entity.basic.ResponseWrapper;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.Map;

// rest api 转发类
public class RestApiForwarding {

    private RestTemplate restTemplate;

    private ResponseWrapper toGet(String url, Map<String, String> requestParams, String token) {
        MultiValueMap<String, String> requestHeader = new LinkedMultiValueMap<>();
        requestHeader.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        requestHeader.add(HttpHeaders.AUTHORIZATION, token);

        // 拼接query字符串
        String urlSuffixx = "?";
        Iterator<String> keys = requestParams.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            String partOfUrl = key + "=" + requestParams.get(key) + "&";
            urlSuffixx += partOfUrl;
        }
        String requestUrl = url + urlSuffixx;
        try {
            ResponseEntity<Object> responseEntity = restTemplate.getForEntity(requestUrl, Object.class);
            return ResponseWrapper.success(responseEntity);
        } catch (RestClientException e) {
            return ResponseWrapper.fail(e.getMessage());
        }

    }

    private ResponseWrapper toPost(String url, Map<String, String> requestParams, String token) {
        MultiValueMap<String, String> requestHeader = new LinkedMultiValueMap<>();
        requestHeader.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        requestHeader.add(HttpHeaders.AUTHORIZATION, token);

        Map<String, String> requestBody = requestParams;

        HttpEntity<Map> requestHttpEntity = new HttpEntity<>(requestBody, requestHeader);
        try {
            ResponseEntity<Object> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestHttpEntity, Object.class);
            return ResponseWrapper.success(responseEntity);
        } catch (RestClientException e) {
            return ResponseWrapper.fail("RestClientException: " + e.getMessage());
        }
    }


}
