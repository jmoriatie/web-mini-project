package com.api.miniproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class Item {
    @NotNull
    private Long id;

    private String itemName;
    private Integer price;
    private Integer quantity;
    private String buyUrl;
    // 유저아이디
    private Long userId;

    public Item(){

    }

    public Item(String itemName, int price, int quantity, String buyUrl, Long userId) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.buyUrl = buyUrl;
        this.userId = userId; // 해당 유저 소유 아이템
    }

    public void update(Item item){
        this.itemName = item.getItemName();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
        this.buyUrl = item.getBuyUrl();
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", buyUrl='" + buyUrl + '\'' +
                ", userId=" + userId +
                '}';
    }
}
