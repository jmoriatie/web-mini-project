package com.api.miniproject.converter.item;

import com.api.miniproject.domain.Item;
import com.api.miniproject.dto.item.ItemUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class ItemToItemUpdateDtoConverter implements Converter<Item, ItemUpdateDto> {

    @Override
    public ItemUpdateDto convert(Item source) {
        log.info("convert to source={} ", source);
        ItemUpdateDto itemUpdateDto = new ItemUpdateDto();

        itemUpdateDto.setId(source.getId());
        itemUpdateDto.setItemName(source.getItemName());
        itemUpdateDto.setPrice(source.getPrice());
        itemUpdateDto.setQuantity(source.getQuantity());
        itemUpdateDto.setBuyUrl(source.getBuyUrl());

        return itemUpdateDto;
    }
}
