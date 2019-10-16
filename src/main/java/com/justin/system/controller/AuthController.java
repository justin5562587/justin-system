package com.justin.system.controller;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.request.LoginDTO;
import com.justin.system.entity.request.ReqCreateUserDTO;
import com.justin.system.service.AuthService;
import com.justin.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseWrapper login(@RequestBody LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }

    @GetMapping("/logout")
    public ResponseWrapper logout() {
        return authService.logout();
    }

    @PostMapping("/register")
    public ResponseWrapper register(@RequestBody ReqCreateUserDTO params) {
        return userService.createUser(params);
    }
}
