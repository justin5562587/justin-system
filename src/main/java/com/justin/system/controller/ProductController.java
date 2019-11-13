package com.justin.system.controller;

import com.alibaba.fastjson.JSON;
import com.justin.system.models.Product;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RequestMapping(value = "product")
@RestController
public class ProductController {

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/")
    public String getProduct() {
        try {
//            return postLocalLogin();
            return getLocal();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    private String postLocalLogin() {
        String url = "http://localhost:9999/login";

        MultiValueMap<String, String> requestHeader = new LinkedMultiValueMap<>();
        requestHeader.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.getType());

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("email", "root@example.com");
        requestBody.add("password", "123456");
        HttpEntity<MultiValueMap> request = new HttpEntity<>(requestBody, requestHeader);
        ResponseEntity<String> responseEntity;

        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            System.out.println(responseEntity);
            return JSON.toJSONString(responseEntity);

        } catch (RestClientException e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            return e.toString();
        }
    }

    private String getLocal() {
        String url = "http://localhost:9999/blog/list?page=0&size=10";
        String token = "..";
        MultiValueMap<String, String> requestHeader = new LinkedMultiValueMap<>();
        requestHeader.add(HttpHeaders.AUTHORIZATION, token);
        requestHeader.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.getType());

        try {
            ResponseEntity<Object> responseEntity = restTemplate.getForEntity(url, Object.class);
            return JSON.toJSONString(responseEntity);
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
            return e.toString();
        }

    }

    @PostMapping("/")
    public String postProduct(Product product) {
        return JSON.toJSONString(product);
    }
}
