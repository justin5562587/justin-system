package com.justin.system.controller;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.request.ReqCreateUserDTO;
import com.justin.system.entity.request.ReqUpdateUserDTO;
import com.justin.system.entity.search.SearchUserDTO;
import com.justin.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseWrapper getUserList() {
        return userService.getUserList();
    }

    @GetMapping("/detail")
    public ResponseWrapper getUser(SearchUserDTO searchUserDTO) {
        return userService.getUserDetail(searchUserDTO);
    }

    @PostMapping("/create")
    public ResponseWrapper createUser(@RequestBody ReqCreateUserDTO params) {
        return userService.createUser(params);
    }

    @PutMapping("/update")
    public ResponseWrapper updateUser(@RequestBody ReqUpdateUserDTO params) {
        return userService.updateUser(params);
    }

    @PutMapping("/update-password")
    public ResponseWrapper updateUserPassword(@RequestBody ReqUpdateUserDTO params) {
        return userService.updateUserPassword(params);
    }

    @DeleteMapping("/delete")
    public ResponseWrapper deleteUser(@RequestParam Long id) {
        return userService.deleteUser(id);
    }
}
