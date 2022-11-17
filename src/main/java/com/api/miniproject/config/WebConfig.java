package com.api.miniproject.config;

import com.api.miniproject.util.converter.item.*;
import com.api.miniproject.util.converter.account.JoinDtoToAccountConverter;
import com.api.miniproject.util.converter.account.AccountToJoinDtoConverter;
import com.api.miniproject.util.log.LogInterceptor;
import com.api.miniproject.util.loginCheck.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ItemSaveDtoToItemConverter());
        registry.addConverter(new ItemUpdateDtoToItemConverter());

        registry.addConverter(new ItemUpdateAPIDtoToItemConverter());
        registry.addConverter(new JoinDtoToAccountConverter());
        registry.addConverter(new AccountToJoinDtoConverter());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error");

//        registry.addInterceptor(new LoginInterceptor())
//                .order(2)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/login", "/css/**", "/js/**","/error", "/*.ico",  "/join/**", "/v1/**"); // 제외시킬 경로
    }
}
