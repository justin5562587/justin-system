package com.justin.system.entity.request;

import lombok.Data;

@Data
public class ReqCreateUserDTO {

    private String username;
    private String password;
    private String email;
    private String userType;
    private Long points;
}
