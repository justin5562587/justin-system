package com.justin.system;

import com.justin.system.entity.utils.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.ArrayList;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // 设置token拦截器
    @Bean
    TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(tokenInterceptor());
        ArrayList<String> excludedPath = new ArrayList<>();
        excludedPath.add("/login");
        interceptorRegistration.excludePathPatterns(excludedPath);
    }

    // 设置websocket端口监听器
    @Bean
    public ServerEndpointExporter serverEndpointConfig() {
        return new ServerEndpointExporter();
    }
}
