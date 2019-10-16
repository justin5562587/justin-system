package com.justin.system.controller;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.request.ReqCreateUserDTO;
import com.justin.system.entity.request.ReqUpdateUserDTO;
import com.justin.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseWrapper getUserList() { return userService.getUserList(); }

    @GetMapping("/")
    public ResponseWrapper getUser(@RequestParam Long id) {
        return userService.getUserDetail(id);
    }

    @PostMapping("/")
    public ResponseWrapper createUser(@RequestBody ReqCreateUserDTO params) {
        return userService.createUser(params);
    }

    @PutMapping("/")
    public ResponseWrapper updateUser(@RequestBody ReqUpdateUserDTO params) {
        return userService.updateUser(params);
    }

    @DeleteMapping("/")
    public ResponseWrapper deleteUser(@RequestParam Long id) {
        return userService.deleteUser(id);
    }
}
