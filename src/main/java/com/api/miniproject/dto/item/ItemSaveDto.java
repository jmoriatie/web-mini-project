package com.api.miniproject.dto.item;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter @Setter
public class ItemSaveDto{

    @NotBlank
    private String itemName;

    @NumberFormat(pattern = "###,###")
    @NotNull
    @Range(min = 10)
    private Integer price;

    @NumberFormat(pattern = "###,###")
    @NotNull
    @Range(min = 0)
    private Integer quantity;

//    @URL // 알아보기
    private String buyUrl;
    private Long userId;

    public ItemSaveDto(){}

    public ItemSaveDto(String itemName, Integer price, Integer quantity, String buyUrl, Long userId) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.buyUrl = buyUrl;
        this.userId = userId;
    }

    public ItemSaveDto(String itemName, Integer price, Integer quantity, String buyUrl) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.buyUrl = buyUrl;
    }

    @Override
    public String toString() {
        return "ItemSaveDto{" +
                "itemName='" + itemName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", buyUrl='" + buyUrl + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
