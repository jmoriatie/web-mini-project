package com.api.miniproject.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class ItemUpdateDto{

    @NotNull
    private Long id;

    @NotBlank
    private String itemName;

    @NotNull
    @Range(min = 10)
    private Integer price;

    @NotNull
    @Range(min = 0)
    private Integer quantity;

    private String buyUrl;

    // typeMismatch 일 때는 spring 에서 오류 메세지를 넣어주는데 그 떄, 빈객체를 가지고 뭔가를 만드나봐!!!
    public ItemUpdateDto(){}

    public ItemUpdateDto(Long id, String itemName, Integer price, Integer quantity, String buyUrl) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.buyUrl = buyUrl;
    }

    @Override
    public String toString() {
        return "ItemUpdateDto{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", buyUrl='" + buyUrl + '\'' +
                '}';
    }
}
