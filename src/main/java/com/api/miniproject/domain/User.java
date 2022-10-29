package com.api.miniproject.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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

    @NotBlank
    @Column(name = "USER_ID", unique = true)
    String userId;

    @NotBlank
    @Column(name = "USER_PW")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}")
    String userPw;

    @NotEmpty
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
