package com.justin.system.entity.utils;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.basic.SystemConstant;
import com.justin.system.models.User;
import com.justin.system.repository.UserRepository;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletResponse;
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

    private static final Logger logger = Logger.getLogger(TokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        boolean ret;

        String token = req.getHeader("Authorization");

        // 开发时无需token，测试or部署时需要改成false
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

    private void response401(HttpServletResponse res, String message) {
        res.setStatus(HttpStatus.UNAUTHORIZED.value());
        res.setCharacterEncoding("UTF-8");
        res.setContentType("application/json; charset=utf-8");
        Writer out;
        try {
            out = new BufferedWriter(new OutputStreamWriter(res.getOutputStream()));
            ResponseWrapper result = new ResponseWrapper();
            result.setCode(401);
            result.setMessage(message);
            result.setStatus("fail");
            result.setData(message);
            out.append(result.toString());
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
    }

    private void response401(ServletResponse servletResponse, String message) {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        Writer out;
        try {
            out = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
            ResponseWrapper result = new ResponseWrapper();
            result.setCode(401);
            result.setMessage(message);
            result.setStatus("fail");
            result.setData(message);
            out.append(result.toString());
            out.close();
        } catch (IOException e) {
            throw new AuthorizationException("直接返回Response信息出现以下IOException异常" + e.toString());
        }
    }

}
