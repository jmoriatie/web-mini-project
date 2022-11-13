package com.api.miniproject.controller.account;

import com.api.miniproject.dto.account.JoinDto;
import com.api.miniproject.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
            @Validated @RequestBody JoinDto joinDto){

        return ResponseEntity.ok(accountService.join(joinDto));
    }
}
