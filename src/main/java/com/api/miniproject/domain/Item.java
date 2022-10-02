package com.api.miniproject.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter @Setter
public class Item {

    private Long id;
    @NotBlank
    private String itemName;
    @NumberFormat(pattern = "###,###")
    private Integer price;
    @NumberFormat(pattern = "###,###")
    private Integer quantity;
    // 구입한 URL
    private String buyUrl;

    // 최종 업데이트 날짜
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime localDateTime;
    // 유저아이디
    private Long userId;


    public Item(){
        this.localDateTime = LocalDateTime.now();
    }

    public Item(String itemName, int price, int quantity, String buyUrl, Long userId) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.buyUrl = buyUrl;
        this.userId = userId; // 해당 유저 소유 아이템
        this.localDateTime = LocalDateTime.now();
    }

    public Item(Item item) {
        this.itemName = item.getItemName();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
        this.buyUrl = item.getBuyUrl();
        this.userId = item.getUserId();
        this.localDateTime = LocalDateTime.now();
    }

    public void update(Item item){
        this.itemName = item.getItemName();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
        this.buyUrl = item.getBuyUrl();
        this.localDateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", buyUrl='" + buyUrl + '\'' +
                ", localDateTime=" + localDateTime +
                ", userId=" + userId +
                '}';
    }
}
