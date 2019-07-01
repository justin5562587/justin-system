package com.justin.system.controller;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseWrapper login() {
        return authService.login();
    }

    @GetMapping("/logout")
    public ResponseWrapper logout() {
        return authService.logout();
    }
}
