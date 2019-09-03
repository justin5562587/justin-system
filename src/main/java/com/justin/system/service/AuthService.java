package com.justin.system.service;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.request.ReqContactDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public ResponseWrapper login() {
        return ResponseWrapper.success("login successfully");
    }

    public ResponseWrapper logout() {
        return ResponseWrapper.success("logout successfully");
    }

    public ResponseWrapper contact(ReqContactDTO params) {
        return ResponseWrapper.success(params);
    }
}
