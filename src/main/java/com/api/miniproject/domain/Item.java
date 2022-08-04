package com.api.miniproject.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item {
    private Long id;
    private String itemName;
    private int price;
    private int quantity;
    private String buyUrl;

    public Item(String itemName, int price, int quantity, String buyUrl) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.buyUrl = buyUrl;
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
                '}';
    }
}
