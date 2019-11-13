package com.justin.system.entity.utils;

import org.apache.shiro.authc.AuthenticationToken;

@Deprecated
public class JwtToken implements AuthenticationToken {

    private String token;

    JwtToken(String token) {
        this.token = token;
    }

    public Object getPrincipal() {
        return token;

    }

    public Object getCredentials() {
        return token;
    }

}
