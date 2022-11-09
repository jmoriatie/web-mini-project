package com.api.miniproject.controller;

import com.api.miniproject.domain.Account;
import com.api.miniproject.util.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {

    // item 관련 메서드에 접근하기 전에 login 확인하는 aop 작성 -> interceptor로 바꿈..
    @GetMapping
    public String home(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) Account loginAccount,
                       Model model) {
        // 오래 접속해 있던 페이지, 세션이 유지되지 않고 만료됐다면
        if(loginAccount == null){
            return "/login/loginForm";
        }
        log.info("loginUser id:{}, pw:{}, name:{}", loginAccount.getAccountId(), loginAccount.getAccountPw(), loginAccount.getAccountName());
        model.addAttribute("userName", loginAccount.getAccountName());

        return "index";
    }
}
