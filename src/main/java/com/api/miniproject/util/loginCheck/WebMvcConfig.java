package com.api.miniproject.util.loginCheck;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    List<String> paths = new ArrayList<>();;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        setPaths();
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns(paths)
                .excludePathPatterns("/login", "/logout"); // 제외시킬 경로
    }

    private void setPaths(){
        paths.add("/*");
        paths.add("/item/*");
        paths.add("/item/*/edit");
        paths.add("/item/*/delete");
    }
}
