package com.api.miniproject;

import com.api.miniproject.converter.item.ItemSaveDtoToItemConverter;
import com.api.miniproject.converter.item.ItemUpdateDtoToItemConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ItemSaveDtoToItemConverter());
        registry.addConverter(new ItemUpdateDtoToItemConverter());

        //registry.addConverter(new ItemToItemUpdateDtoConverter()); // 필요할 경우 주석 해제
        //registry.addConverter(new ItemToItemSaveDtoConverter()); // 필요할 경우 주석 해제
    }
}
