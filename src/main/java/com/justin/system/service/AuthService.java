package com.justin.system.service;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.request.LoginDTO;
import com.justin.system.entity.utils.JwtUtil;
import com.justin.system.models.User;
import com.justin.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    public ResponseWrapper login(LoginDTO loginDTO) {
        if (loginDTO.getEmail() == null) {
            return ResponseWrapper.fail("Need Email");
        } else if (loginDTO.getPassword() == null) {
            return ResponseWrapper.fail("Need Password");
        }
        Optional<User> optionalUser = userRepository.findByEmail(loginDTO.getEmail());
        if (optionalUser.isPresent()) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            boolean ifMatch = bCryptPasswordEncoder.matches(loginDTO.getPassword(), optionalUser.get().getPassword());
            if (!ifMatch) {
                return ResponseWrapper.fail("Password Incorrect");
            }
            Map<String, Object> result = new HashMap<>();
            String token = JwtUtil.generateToken(optionalUser.get());
            result.put("token", token);
            return ResponseWrapper.success(result);
        } else {
            return ResponseWrapper.fail("Email Not Existed");
        }
    }

    public ResponseWrapper logout() {
        return ResponseWrapper.success("Logout Successfully");
    }
}
