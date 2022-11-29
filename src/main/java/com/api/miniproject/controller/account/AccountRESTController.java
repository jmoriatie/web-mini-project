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
import org.springframework.validation.BindingResult;
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
            @Validated @RequestBody JoinDto joinDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            log.error("bindingResult error={}", bindingResult);
            // TODO: new Exception 추가
        }

        JoinDto joinedAccount = accountService.join(joinDto);

        // TODO: Runtime Exception 돌려주도록 변경
        return ResponseEntity.ok(joinedAccount);
    }

    //TODO: 인가조건에 따라 들어갈 수 있는 페이지와 없는 페이지 만들기 + 나누기
    //TODO: restapi 관련 회원가입 및 로그인페이지도 구현
    //TODO: 더미데이터 추가
    //TODO: 이거 추가하고 Readme 수정 후 종료

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

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getAccountId(), loginDto.getAccountPw());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok(LoginDto.from(foundAccount));
    }

    @PostMapping("/createAccount")
    public ResponseEntity createUserForAdmin(@Validated @RequestBody JoinDto joinDto){

//        if(bindingResult.hasErrors()){
//            throw new ErrorResponse(ErrorCode.SERVER_ERROR);
//            throw new AccountAPIException(bindingResult.hasErrors());
//            throw new MethodArgumentNotValidException(bindingResult);
//        }
        JoinDto createdAccount = accountService.createUserForAdmin(joinDto);
        return ResponseEntity.ok(createdAccount);
    }
}
