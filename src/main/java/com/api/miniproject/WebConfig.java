package com.api.miniproject;

import com.api.miniproject.util.converter.item.ItemSaveDtoToItemConverter;
import com.api.miniproject.util.converter.item.ItemUpdateAPIDtoToItemConverter;
import com.api.miniproject.util.converter.item.ItemUpdateDtoToItemConverter;
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
        //registry.addConverter(new ItemToItemUpdateDtoConverter()); // 필요할 경우 주석 해제
        //registry.addConverter(new ItemToItemSaveDtoConverter()); // 필요할 경우 주석 해제
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error");

        registry.addInterceptor(new LoginInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/css/**", "/js/**","/error", "/*.ico", "/v1/item-api/**"); // 제외시킬 경로
    }
}
