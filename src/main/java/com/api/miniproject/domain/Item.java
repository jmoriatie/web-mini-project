package com.api.miniproject.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "ITEM_TB")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String itemName;

    @Column(name = "PRICE")
    @NumberFormat(pattern = "###,###")
    @Min(value = 10)
    @Max(value = 100000000)
    private Integer price;

    @NumberFormat(pattern = "###,###")
    @Min(value = 0)
    private Integer quantity;

    // 구입한 URL
    private String buyUrl;

    // 최종 업데이트 날짜
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime lastUpdateDate;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime createdAt;

    // 계정아이디
    private String accountId;

    @Builder
    public Item(String itemName, int price, int quantity, String buyUrl, String accountId) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.buyUrl = buyUrl;
        this.accountId = accountId; // 해당 유저 소유 아이템
        this.lastUpdateDate = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Transactional
    public void update(Item item){
        this.itemName = item.getItemName();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
        this.buyUrl = item.getBuyUrl();
        this.lastUpdateDate = LocalDateTime.now();
    }
}
