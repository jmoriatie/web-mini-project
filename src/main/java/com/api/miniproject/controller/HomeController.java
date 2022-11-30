package com.api.miniproject.controller;

import com.api.miniproject.domain.Account;
import com.api.miniproject.util.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home(@AuthenticationPrincipal UserDetails authenticatedAccount,
                       Model model) {
        // 오래 접속해 있던 페이지, 세션이 유지되지 않고 만료됐다면
        if(authenticatedAccount == null){
            return "redirect:/login";
        }
        log.info("loginAccount id:{}, auth:{}", authenticatedAccount.getUsername(), authenticatedAccount.getAuthorities().toString());
        model.addAttribute("accountId", authenticatedAccount.getUsername());

        return "index";
    }
}
