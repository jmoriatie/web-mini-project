package com.api.miniproject.controller.account;

import com.api.miniproject.domain.Account;
import com.api.miniproject.dto.account.JoinDto;
import com.api.miniproject.dto.account.LoginDto;
import com.api.miniproject.service.account.AccountService;
import com.api.miniproject.exception.exceptions.AccountAPIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/account-api")
public class AccountRESTController {

    private final AccountService accountService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/join")
    public ResponseEntity<JoinDto> join(
            @Validated @RequestBody JoinDto joinDto){

        JoinDto joinedAccount = accountService.join(joinDto);
        return ResponseEntity.ok(joinedAccount);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDto> login(@Validated @RequestBody LoginDto loginDto){
        Account foundAccount = accountService.findByAccountId(loginDto.getAccountId());

        SecurityContextHolder.getContext().setAuthentication(this.getAuthentication(loginDto));
        return ResponseEntity.ok(LoginDto.from(foundAccount));
    }

    private Authentication getAuthentication(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getAccountId(), loginDto.getAccountPw());
        return authenticationManager.authenticate(authenticationToken);
    }

    @PostMapping("/createAccount")
    public ResponseEntity<JoinDto> createUserForAdmin(@Validated @RequestBody JoinDto joinDto){

        JoinDto createdAccount = accountService.createUserForAdmin(joinDto);
        return ResponseEntity.ok(createdAccount);
    }
}
