package com.justin.system.service;

import com.justin.system.entity.basic.ResponseWrapper;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public ResponseWrapper login() {
        return ResponseWrapper.successRender("login successfully");
    }

    public ResponseWrapper logout() {
        return ResponseWrapper.successRender("logout successfully");
    }
}
