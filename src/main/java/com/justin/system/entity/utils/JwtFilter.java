package com.justin.system.entity.utils;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.basic.SystemConstant;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class JwtFilter extends BasicHttpAuthenticationFilter {

    private static String refreshTokenExpireTime;

    public void setRefreshTokenExpireTime(String refreshTokenExpireTime) {
        JwtFilter.refreshTokenExpireTime = refreshTokenExpireTime;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        // 判断用户是否想要登录
        if (isLoginAttempt(request, response)) {
            try {
                // 进行Shiro的登录UserRealm
                this.executeLogin(request, response);
            } catch (Exception e) {
                String mes = e.getMessage();
                Throwable throwable = e.getCause();
            }
        } else {
            String message = "用户未登录";
            this.response401(response, message);
            return false;
        }
        return true;
    }

    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        String token = this.getAuthzHeader(request);
        return token != null;
    }

    // todo
    protected boolean excuteLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader(SystemConstant.REQUEST_AUTH_HEADER);
        JwtToken token = new JwtToken(authorization);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        this.getSubject(request, response).login(token);
        // 绑定上下文
        String username = JwtUtil.getClaim(authorization, SystemConstant.USER_NAME);
        String email = JwtUtil.getClaim(authorization, SystemConstant.USER_EMAIL);
        String userId = JwtUtil.getClaim(authorization, SystemConstant.USER_ID);
        return false;
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

    // 提供跨域支持
    protected boolean preHandle(ServletRequest req, ServletResponse res) throws Exception {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        // 跨域请求会先发送OPTIONS请求进行检测，这里让起直接通过返回正常状态
        response.setHeader("Access-control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            response.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(req, res);
    }
}
