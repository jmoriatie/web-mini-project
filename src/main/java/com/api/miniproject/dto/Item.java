package com.api.miniproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item {
    private Long id;
    private String itemName;
    private int price;
    private int amount;

    public Item(String itemName, int price, int amount) {
        this.itemName = itemName;
        this.price = price;
        this.amount = amount;
    }

    public void update(String itemName, int price, int amount){
        this.itemName = itemName;
        this.price = price;
        this.amount = amount;
    }
}
