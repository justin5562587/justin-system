package com.justin.system.service;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.request.ReqContactDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public ResponseWrapper login() {
        return ResponseWrapper.successRender("login successfully");
    }

    public ResponseWrapper logout() {
        return ResponseWrapper.successRender("logout successfully");
    }

    public ResponseWrapper contact(ReqContactDTO params) {
        return ResponseWrapper.successRender(params);
    }
}
