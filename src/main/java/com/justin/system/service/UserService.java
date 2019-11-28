package com.justin.system.service;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.enums.ErrorTypeEnum;
import com.justin.system.entity.request.ReqCreateUserDTO;
import com.justin.system.entity.request.ReqUpdateUserDTO;
import com.justin.system.mapper.UserMapper;
import com.justin.system.models.User;
import com.justin.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public ResponseWrapper getUserList() {
        List<User> userList = userMapper.getUserList();
        return ResponseWrapper.success(userList);
    }

    public ResponseWrapper getUserDetail(Long id) {
        User retUser = userMapper.getUserById(id);
        return retUser != null ?
                ResponseWrapper.success(retUser) :
                ResponseWrapper.fail("User Not Found");
    }

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

            newUser.setUserType(params.getUserType());
            newUser.setEmail(params.getEmail());
            newUser.setUsername(params.getUsername());
            newUser.setPoints(params.getPoints());

            userMapper.save(newUser);

            return ResponseWrapper.success("Create User Successfully");
        } catch (Exception e) {
            return ResponseWrapper.fail(e.getCause() + e.getMessage());
        }
    }

    public ResponseWrapper updateUser(ReqUpdateUserDTO params) {
        return null;
    }

    public ResponseWrapper deleteUser(Long id) {
        try {
            userMapper.deleteUserById(id);
            return ResponseWrapper.success("Delete User Successfully");
        } catch (Exception e) {
            return ResponseWrapper.fail(e.getCause());
        }
    }

}
