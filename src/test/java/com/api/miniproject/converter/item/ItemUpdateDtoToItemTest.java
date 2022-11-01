package com.api.miniproject.converter.item;

import com.api.miniproject.domain.Item;
import com.api.miniproject.dto.item.ItemUpdateDto;
import com.api.miniproject.util.converter.item.ItemUpdateDtoToItemConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ItemUpdateDtoToItem 관련 테스트")
class ItemUpdateDtoToItemTest {

    DefaultConversionService conversionService = new DefaultConversionService();

    @Test
    @DisplayName("ItemUpdateDtoToItemConverter 테스트")
    void convert() {
        conversionService.addConverter(new ItemUpdateDtoToItemConverter());

        ItemUpdateDto itemUpdateDto = new ItemUpdateDto(1L, "itemB", 1000, 10, "testB@test.com", LocalDateTime.now());

        Item itemB = conversionService.convert(itemUpdateDto, Item.class);

        assert itemB != null;
        assertThat(itemB).isInstanceOf(Item.class);
        assertThat(itemB.getItemName()).isEqualTo("itemB");
        assertThat(itemB.getPrice()).isEqualTo(1000);
        assertThat(itemB.getQuantity()).isEqualTo(10);
        assertThat(itemB.getBuyUrl()).isEqualTo("testB@test.com");
    }
}