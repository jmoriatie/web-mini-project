package com.api.miniproject.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "USER_TB")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "USER_ID")
    String userId;

    @Column(name = "USER_PW")
    String userPw;

    @Column(name = "USER_NAME")
    String userName;

    @Column(name = "CREATE_TIME")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    LocalDateTime createDate;

    @Builder
    public User(String userId, String userPw, String userName) {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.createDate = LocalDateTime.now();
    }

    public void update(User user) {
        this.userPw = userPw;
        this.userName = userName;
    }
}
