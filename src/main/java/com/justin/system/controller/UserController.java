package com.justin.system.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @GetMapping("/detail")
    public String getUser(@RequestParam Long id) {
        return "get user successfully";
    }

    @GetMapping("/list")
    public String getUserList() {
        return "get user list successfully";
    }

    @PostMapping("/")
    public String createUser() {
        return "create user successfully";
    }

    @PutMapping("/")
    public String updateUser() {
        return "update user successfully";
    }

    @DeleteMapping("/")
    public String deleteUser(@RequestParam Long id) {
        return "delete user successfully";
    }
}
