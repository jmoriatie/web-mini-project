package com.api.miniproject.util.converter.item;

import com.api.miniproject.domain.Item;
import com.api.miniproject.dto.item.ItemSaveDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class ItemSaveDtoToItemConverter implements Converter<ItemSaveDto, Item> {

    @Override
    public Item convert(ItemSaveDto source) {
        log.info("convert to source={} ", source);
        Item item = Item.builder()
                .itemName(source.getItemName())
                .price(source.getPrice())
                .quantity(source.getQuantity())
                .buyUrl(source.getBuyUrl()).build();

        if (source.getAccountId() != null) {
            item.setAccountId(source.getAccountId());
        }

        return item;
    }
}
