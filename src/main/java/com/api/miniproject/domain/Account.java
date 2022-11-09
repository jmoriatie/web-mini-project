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
@Table(name = "ACCOUNT_TB")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    @Column(unique = true)
    String accountId;

    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}")
    String accountPw;

    @NotEmpty
    String accountName;

    String auth;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime lastAccessDate;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    LocalDateTime createDate;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    LocalDateTime lastUpdateDate;

    @Builder
    public Account(String accountId, String accountPw, String accountName, String auth) {
        this.accountId = accountId;
        this.accountPw = accountPw;
        this.accountName = accountName;
        this.auth = auth;
    }

    public void update(Account Account) {
        this.accountPw = accountPw;
        this.accountName = accountName;
    }
}
