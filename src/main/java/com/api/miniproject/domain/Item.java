package com.api.miniproject.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

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
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ITEM_NAME")
    @NotBlank
    private String itemName;

    @Column(name = "PRICE")
    @NumberFormat(pattern = "###,###")
    @Min(value = 10)
    @Max(value = 100000000)
    private Integer price;

    @Column(name = "QUANTITY")
    @NumberFormat(pattern = "###,###")
    @Min(value = 0)
    private Integer quantity;

    // 구입한 URL
    @Column(name = "BUY_URL")
    private String buyUrl;

    // 최종 업데이트 날짜
    @Column(name = "LOCAL_DATE_TIME")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime localDateTime;

    // TODO : 추후 Forign key 로 변경 필요
    // 유저아이디
    @Column(name = "USER_ID")
    private Long userId;

    @Builder
    public Item(String itemName, int price, int quantity, String buyUrl, Long userId) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.buyUrl = buyUrl;
        this.userId = userId; // 해당 유저 소유 아이템
        this.localDateTime = LocalDateTime.now();
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void update(Item item){
        this.itemName = item.getItemName();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
        this.buyUrl = item.getBuyUrl();
        this.localDateTime = LocalDateTime.now();
    }

    // TODO 연관관계 메서드
}
