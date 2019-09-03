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
        return ResponseWrapper.success(foundUsers);
    }

    public ResponseWrapper getUserDetail(Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.isPresent() ?
                ResponseWrapper.success(foundUser) :
                ResponseWrapper.fail(ErrorTypeEnum.CAN_NOT_FOUND.getDescription());
    }

    @Transactional
    public ResponseWrapper createUser(ReqCreateUserDTO params) {
        try {
            User newUser = new User();

            Iterable<User> userIterable = userRepository.findAll();

            // handle email && username
            String email = params.getEmail();
            String username = params.getUsername();
            for (User user : userIterable) {
                if (user.getEmail().equals(email)) {
                    return ResponseWrapper.fail("Existed Email");
                }
                if (user.getUsername().equals(username)) {
                    return ResponseWrapper.fail("Existed Username");
                }
            }
            newUser.setEmail(email);
            newUser.setUsername(username);

            // encode password
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String bCryptPassword = bCryptPasswordEncoder.encode(params.getPassword());
            newUser.setPassword(bCryptPassword);

            // handle time
            Long currentTime = System.currentTimeMillis();
            newUser.setCreateTime(currentTime);
            newUser.setUpdateTime(currentTime);

            newUser.setUserType(params.getUserType());

            User savedUser = userRepository.save(newUser);

            return ResponseWrapper.success(savedUser);
        } catch (Exception e) {
            return ResponseWrapper.fail(ErrorTypeEnum.CREATE_FAILURE.getDescription());
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

                return ResponseWrapper.success("Update user successfully");
            } else {
                return ResponseWrapper.fail(ErrorTypeEnum.CAN_NOT_FOUND.getDescription());
            }
        } catch (Exception e) {
            return ResponseWrapper.fail(ErrorTypeEnum.UPDATE_FAILURE.getDescription());
        }
    }

    @Transactional
    public ResponseWrapper deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return ResponseWrapper.success("delete user successfully");
        } catch (Exception e) {
            return ResponseWrapper.fail(ErrorTypeEnum.DELETE_FAILURE.getDescription());
        }
    }

}
