package com.api.miniproject.converter.item;

import com.api.miniproject.domain.Item;
import com.api.miniproject.dto.item.ItemSaveDto;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemSaveDtoToItemTest {

    DefaultConversionService conversionService = new DefaultConversionService();

    @Test
    void convert() {
        conversionService.addConverter(new ItemSaveDtoToItemConverter());

        ItemSaveDto itemSaveDto = new ItemSaveDto("itemA", 50000, 5, "test@test.com");

        Item itemA = conversionService.convert(itemSaveDto, Item.class);

        assert itemA != null;
        assertThat(itemA).isInstanceOf(Item.class);
        assertThat(itemA.getItemName()).isEqualTo("itemA");
        assertThat(itemA.getPrice()).isEqualTo(50000);
        assertThat(itemA.getQuantity()).isEqualTo(5);
        assertThat(itemA.getBuyUrl()).isEqualTo("test@test.com");
    }
}