package com.api.miniproject.dto.item;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class ItemUpdateDto{

    @NotNull
    private Long id;

    @NotBlank
    private String itemName;

    @NumberFormat(pattern = "###,###")
    @NotNull
    @Min(value = 10)
    @Max(value = 100000000)
    private Integer price;

    @NumberFormat(pattern = "###,###")
    @NotNull
    @Min(value = 0)
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
}
