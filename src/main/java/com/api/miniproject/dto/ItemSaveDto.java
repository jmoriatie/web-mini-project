package com.api.miniproject.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class ItemSaveDto{

    @NotBlank
    private String itemName;

    @NotNull
    @Range(min = 10)
    private Integer price;

    @NotNull
    @Range(min = 0)
    private Integer quantity;

    @URL // 알아보기
    private String buyUrl;

    private Long userId;

    public ItemSaveDto(String itemName, Integer price, Integer quantity, String buyUrl, Long userId) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.buyUrl = buyUrl;
        this.userId = userId;
    }
}
