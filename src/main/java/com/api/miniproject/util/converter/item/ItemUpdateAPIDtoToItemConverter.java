package com.api.miniproject.util.converter.item;

import com.api.miniproject.domain.Item;
import com.api.miniproject.dto.item.ItemUpdateAPIDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class ItemUpdateAPIDtoToItemConverter implements Converter<ItemUpdateAPIDto, Item> {
    @Override
    public Item convert(ItemUpdateAPIDto source) {
        log.info("convert to source={} ", source);
        Item item = new Item();

        item.setItemName(source.getItemName());
        item.setPrice(source.getPrice());
        item.setQuantity(source.getQuantity());
        item.setBuyUrl(source.getBuyUrl());

        return item;
    }
}
