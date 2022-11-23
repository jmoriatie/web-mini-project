package com.api.miniproject.controller.account;

import com.api.miniproject.domain.Account;
import com.api.miniproject.dto.account.JoinDto;
import com.api.miniproject.dto.account.LoginDto;
import com.api.miniproject.service.account.AccountService;
import com.api.miniproject.util.exceptionhandler.exception.AccountAPIException;
import com.api.miniproject.util.exceptionhandler.exception.ItemAPIBindException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/account-api")
public class AccountRESTController {

    private final AccountService accountService;

    @PostMapping("/join")
    public ResponseEntity<JoinDto> join(
            @Validated @RequestBody JoinDto joinDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            log.error("bindingResult error={}", bindingResult);
            // TODO: new Exception 추가
        }
        return ResponseEntity.ok(accountService.join(joinDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDto> login(@Validated @RequestBody LoginDto loginDto, BindingResult bindingResult){
        Account foundAccount = accountService.findByAccountId(loginDto.getAccountId());

        if(bindingResult.hasErrors()){
            log.error("bindingResult error={}", bindingResult);
            // TODO: new Exception 추가
        }
        if(foundAccount == null){
            throw new AccountAPIException();
        }
        return ResponseEntity.ok(LoginDto.from(foundAccount));
    }
}
