package com.justin.system.service;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.request.LoginDTO;
import com.justin.system.entity.search.SearchUserDTO;
import com.justin.system.entity.utils.JwtUtil;
import com.justin.system.mapper.UserMapper;
import com.justin.system.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private UserMapper userMapper;

    public ResponseWrapper login(LoginDTO loginDTO) {
        if (loginDTO.getEmail() == null) {
            return ResponseWrapper.fail("Need Email");
        } else if (loginDTO.getPassword() == null) {
            return ResponseWrapper.fail("Need Password");
        }

        SearchUserDTO searchUserDTO = new SearchUserDTO();
        searchUserDTO.setEmail(loginDTO.getEmail());
        User user = userMapper.getUserByParams(searchUserDTO);

        if (user == null) {
            return ResponseWrapper.fail("User Not Found");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean isPasswordMatch = bCryptPasswordEncoder.matches(loginDTO.getPassword(), user.getPassword());

        if (isPasswordMatch) {
            Map<String, Object> result = new HashMap<>();
            String token = JwtUtil.generateToken(user);
            result.put("token", token);
            return ResponseWrapper.success(result);
        } else {
         return ResponseWrapper.fail("Password Not Matched");
        }
    }

    public ResponseWrapper logout() {
        return ResponseWrapper.success("Logout Successfully");
    }
}
