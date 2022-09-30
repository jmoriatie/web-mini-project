package com.api.miniproject.converter.item;

import com.api.miniproject.domain.Item;
import com.api.miniproject.dto.item.ItemSaveDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class ItemToItemSaveDto implements Converter<Item, ItemSaveDto> {

    @Override
    public ItemSaveDto convert(Item source) {
        log.info("convert to source={} ", source);
        ItemSaveDto itemSaveDto = new ItemSaveDto();
        itemSaveDto.setItemName(source.getItemName());
        itemSaveDto.setPrice(source.getPrice());
        itemSaveDto.setQuantity(source.getQuantity());
        itemSaveDto.setBuyUrl(source.getBuyUrl());
        return itemSaveDto;
    }
}
