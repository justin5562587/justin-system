package com.justin.system.entity.request;

import lombok.Data;

@Data
public class ReqUpdateUserDTO {

    // 至少要带有id
    private Long id;
    private String userType;
    private Long points;
    private String password;
    private String oldPassword;
    private Long updateTime;

}
