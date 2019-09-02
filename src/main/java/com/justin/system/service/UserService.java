package com.justin.system.service;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.enums.ErrorTypeEnum;
import com.justin.system.entity.request.ReqCreateUserDTO;
import com.justin.system.entity.request.ReqUpdateUserDTO;
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
                ResponseWrapper.failRender(ErrorTypeEnum.CAN_NOT_FOUND.getDescription());
    }

    @Transactional
    public ResponseWrapper createUser(ReqCreateUserDTO params) {
        try {
            User newUser = new User();
            // encode password
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String bCryptPassword = bCryptPasswordEncoder.encode(params.getPassword());
            newUser.setPassword(bCryptPassword);

            // handle time
            Long currentTime = System.currentTimeMillis();
            newUser.setCreateTime(currentTime);
            newUser.setUpdateTime(currentTime);

            newUser.setUsername(params.getUsername());
            newUser.setUserType(params.getUserType());
            newUser.setEmail(params.getEmail());

            User savedUser = userRepository.save(newUser);

            return ResponseWrapper.successRender(savedUser);
        } catch (Exception e) {
            return ResponseWrapper.failRender(ErrorTypeEnum.CREATE_FAILURE.getDescription());
        }
    }

    @Transactional
    public ResponseWrapper updateUser(ReqUpdateUserDTO params) {
        try {
            Optional<User> optionalUser = userRepository.findById(params.getId());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setUserType(params.getUserType());
                user.setUsername(params.getUsername());
                user.setEmail(params.getEmail());
                userRepository.save(user);

                return ResponseWrapper.successRender("Update user successfully");
            } else {
                return ResponseWrapper.failRender(ErrorTypeEnum.CAN_NOT_FOUND.getDescription());
            }
        } catch (Exception e) {
            return ResponseWrapper.failRender(ErrorTypeEnum.UPDATE_FAILURE.getDescription());
        }
    }

    @Transactional
    public ResponseWrapper deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return ResponseWrapper.successRender("delete user successfully");
        } catch (Exception e) {
            return ResponseWrapper.failRender(ErrorTypeEnum.DELETE_FAILURE.getDescription());
        }
    }

}
