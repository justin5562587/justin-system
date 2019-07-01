package com.justin.system.service;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.request.ReqCreateUserDTO;
import com.justin.system.models.User;
import com.justin.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseWrapper getUserList() {
        Iterable<User> foundUsers = userRepository.findAll();
        return ResponseWrapper.successRender(foundUsers);
    }

    public ResponseWrapper getUserDetail(Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.isPresent() ?
                ResponseWrapper.successRender(foundUser) :
                ResponseWrapper.failRender("error");
    }

    @Transactional
    public ResponseWrapper createUser(ReqCreateUserDTO params) {
        try {
            User newUser = new User();
            // encode password
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String bCryptPassword = bCryptPasswordEncoder.encode(params.getPassword());
            newUser.setPassword(bCryptPassword);

            newUser.setUsername(params.getUsername());
            newUser.setGroupName(params.getGroupName());
            newUser.setEmail(params.getEmail());
            Long currentTime = System.currentTimeMillis();
            newUser.setCreateTime(currentTime);
            newUser.setUpdateTime(currentTime);
            User savedUser = userRepository.save(newUser);

            return ResponseWrapper.successRender(savedUser);
        } catch (Exception e) {
            return ResponseWrapper.failRender("error");
        }
    }

    @Transactional
    public ResponseWrapper updateUser() {
        try {
            return ResponseWrapper.successRender("create user successfully");
        } catch (Exception e) {
            return ResponseWrapper.failRender("error");
        }
    }

    @Transactional
    public ResponseWrapper deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return ResponseWrapper.successRender("delete user successfully");
        } catch (Exception e) {
            return ResponseWrapper.failRender("error");
        }
    }

}
