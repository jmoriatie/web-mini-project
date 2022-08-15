package com.api.miniproject.util.loginCheck;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/*")
                .addPathPatterns("/item/*")
                .addPathPatterns("/item/*/edit")
                .excludePathPatterns("/login", "/logout"); // 제외시킬 경로
    }
}
