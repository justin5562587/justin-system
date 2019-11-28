package com.justin.system.models;

import lombok.Data;

@Data
public class User {

    private Long id;
    private Long createTime;
    private Long updateTime;
    private String username;
    private String password;
    private String email;
    private String userType;
    private Long points;

}
