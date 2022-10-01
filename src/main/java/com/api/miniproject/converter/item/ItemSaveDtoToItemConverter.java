package com.api.miniproject.converter.item;

import com.api.miniproject.domain.Item;
import com.api.miniproject.dto.item.ItemSaveDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class ItemSaveDtoToItemConverter implements Converter<ItemSaveDto, Item> {

    @Override
    public Item convert(ItemSaveDto source) {
        log.info("convert to source={} ", source);
        Item item = new Item();
        item.setItemName(source.getItemName());
        item.setPrice(source.getPrice());
        item.setQuantity(source.getQuantity());
        item.setBuyUrl(source.getBuyUrl());

        return item;
    }
}
