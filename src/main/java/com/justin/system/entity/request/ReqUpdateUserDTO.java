package com.justin.system.entity.request;

import lombok.Data;

@Data
public class ReqUpdateUserDTO {

    private Long id;
    private String username;
//    private String password;
    private String email;
    private String userType;

}
