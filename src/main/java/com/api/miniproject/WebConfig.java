package com.api.miniproject;

import com.api.miniproject.converter.item.ItemSaveDtoToItem;
import com.api.miniproject.converter.item.ItemToItemSaveDto;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ItemSaveDtoToItem());
        registry.addConverter(new ItemToItemSaveDto());
    }
}
