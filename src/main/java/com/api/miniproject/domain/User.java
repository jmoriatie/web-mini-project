package com.api.miniproject.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER_TB")
@Getter
@Setter
public class User {

    @Id
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

    public User() {
        this.createDate = LocalDateTime.now();
    }

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", userPw='" + userPw + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
