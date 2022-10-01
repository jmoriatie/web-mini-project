package com.api.miniproject.dto.item;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ItemUpdateAPIDto {
    private Long id;

    @NotBlank
    private String itemName;

    @NumberFormat(pattern = "###,###")
    @NotNull
    @Min(value = 10)
    private Integer price;

    @NumberFormat(pattern = "###,###")
    @NotNull
    @Min(value = 0)
    private Integer quantity;

    private String buyUrl;

    public ItemUpdateAPIDto() {}

    public ItemUpdateAPIDto(String itemName, Integer price, Integer quantity, String buyUrl) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.buyUrl = buyUrl;
    }

    @Override
    public String toString() {
        return "ItemUpdateAPIDto{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", buyUrl='" + buyUrl + '\'' +
                '}';
    }
}
