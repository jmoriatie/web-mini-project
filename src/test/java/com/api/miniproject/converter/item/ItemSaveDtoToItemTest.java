package com.api.miniproject.converter.item;

import com.api.miniproject.domain.Item;
import com.api.miniproject.dto.item.ItemSaveDto;
import com.api.miniproject.util.converter.item.ItemSaveDtoToItemConverter;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.*;

class ItemSaveDtoToItemTest {

    DefaultConversionService conversionService = new DefaultConversionService();

    @Test
    void convertWithoutUserId() {
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

    @Test
    void convertInUserId() {
        conversionService.addConverter(new ItemSaveDtoToItemConverter());

        ItemSaveDto itemSaveDto = new ItemSaveDto("itemA", 50000, 5, "test@test.com", 1L);

        Item itemA = conversionService.convert(itemSaveDto, Item.class);

        assert itemA != null;
        assertThat(itemA).isInstanceOf(Item.class);
        assertThat(itemA.getItemName()).isEqualTo("itemA");
        assertThat(itemA.getPrice()).isEqualTo(50000);
        assertThat(itemA.getQuantity()).isEqualTo(5);
        assertThat(itemA.getBuyUrl()).isEqualTo("test@test.com");
        assertThat(itemA.getUserId()).isEqualTo(1L);
    }
}