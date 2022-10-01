package com.api.miniproject.converter.item;

import com.api.miniproject.domain.Item;
import com.api.miniproject.dto.item.ItemUpdateDto;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemUpdateDtoToItemTest {

    DefaultConversionService conversionService = new DefaultConversionService();

    @Test
    void convert() {
        conversionService.addConverter(new ItemUpdateDtoToItemConverter());

        ItemUpdateDto itemUpdateDto = new ItemUpdateDto(1L, "itemB", 1000, 10, "testB@test.com");

        Item itemB = conversionService.convert(itemUpdateDto, Item.class);

        assert itemB != null;
        assertThat(itemB).isInstanceOf(Item.class);
        assertThat(itemB.getItemName()).isEqualTo("itemB");
        assertThat(itemB.getPrice()).isEqualTo(1000);
        assertThat(itemB.getQuantity()).isEqualTo(10);
        assertThat(itemB.getBuyUrl()).isEqualTo("testB@test.com");
    }
}