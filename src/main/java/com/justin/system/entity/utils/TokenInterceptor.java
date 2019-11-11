package com.justin.system.entity.utils;

import com.alibaba.fastjson.JSON;
import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.basic.SystemConstant;
import com.justin.system.models.User;
import com.justin.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Optional;

public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserRepository userRepository;

    // token拦截器逻辑
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        boolean ret;

        String token = req.getHeader("Authorization");
        if (token == null || token.equals("")) {
            ret = false;
        } else {
            try {
                String email = JwtUtil.getClaim(token, SystemConstant.USER_EMAIL);
                Optional<User> optionalUser = userRepository.findByEmail(email);
                if (optionalUser.isPresent()) {
                    JwtUtil.validateToken(token, optionalUser.get());
                    ret = true;
                } else {
                    throw new RuntimeException("User Not Existed");
                }
            } catch (Exception e) {
                throw new RuntimeException(e.toString());
            }
        }

        if (!ret) {
            String message = "认证失败";
            this.response401(res, message);
        }

        return ret;
    }

    // 缺失token情况下访问非公开api调用
    private void response401(HttpServletResponse res, String message) {
        res.setStatus(HttpStatus.UNAUTHORIZED.value());
        res.setCharacterEncoding("UTF-8");
        res.setContentType("application/json; charset=utf-8");
        Writer out;
        try {
            out = new BufferedWriter(new OutputStreamWriter(res.getOutputStream()));
            ResponseWrapper result = new ResponseWrapper();
            result.setMessage(message);
            result.setStatus(HttpStatus.UNAUTHORIZED.toString());
            result.setCode(HttpStatus.UNAUTHORIZED.value());
            out.append(JSON.toJSONString(result));
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
    }

    private void response500(HttpServletResponse res, String message) {
        res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        res.setCharacterEncoding("UTF-8");
        res.setContentType("application/json; charset=utf-8");
        Writer out;
        try {
            out = new BufferedWriter(new OutputStreamWriter(res.getOutputStream()));
            ResponseWrapper result = new ResponseWrapper();
            result.setMessage(message);
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            out.append(JSON.toJSONString(result));
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
    }

}
