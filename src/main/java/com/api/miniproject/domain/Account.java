package com.api.miniproject.domain;

import com.api.miniproject.dto.account.JoinDto;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Table(name = "account_tb")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "account_id", unique = true)
    private String accountId;

    @NotBlank
    @Column(name = "account_pw",length = 100)
//    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}")
    private String accountPw;

    @NotEmpty
    @Column(name = "account_name")
    private String accountName;

    @ManyToMany
    @JoinTable(
            name = "account_authority_tb",
            joinColumns = {@JoinColumn(name = "id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime lastAccessDate;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime createDate;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime lastUpdateDate;

    @Builder
    public Account(String accountId, String accountPw, String accountName, Set<Authority> authorities) {
        this.accountId = accountId;
        this.accountPw = accountPw;
        this.accountName = accountName;
        this.authorities = authorities;
        this.createDate = LocalDateTime.now();
        this.lastAccessDate = LocalDateTime.now();
        this.lastUpdateDate = LocalDateTime.now();
    }

    public static Account from(JoinDto dto){
        return Account.builder()
                .accountId(dto.getAccountId())
                .accountPw(dto.getAccountPw())
                .accountName(dto.getAccountName())
                .authorities(dto.getAuthorities().stream()
                        .map(auth -> Authority.builder().authorityName(auth.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }

    @Transactional
    public void update(Account Account) {
        this.accountPw = accountPw;
        this.accountName = accountName;
        this.lastUpdateDate = LocalDateTime.now();
    }
}
