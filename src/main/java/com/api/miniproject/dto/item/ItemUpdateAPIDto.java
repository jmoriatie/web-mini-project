package com.api.miniproject.dto.item;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class ItemUpdateAPIDto {

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
    @Min(value = 10)
    private Integer quantity;
    private String buyUrl;

    public ItemUpdateAPIDto() {}

    public ItemUpdateAPIDto(String itemName, Integer price, Integer quantity, String buyUrl) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.buyUrl = buyUrl;
    }
}
