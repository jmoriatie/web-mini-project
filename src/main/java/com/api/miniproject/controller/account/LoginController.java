package com.api.miniproject.controller.account;

import com.api.miniproject.domain.Account;
import com.api.miniproject.dto.account.LoginDto;
import com.api.miniproject.service.login.LoginService;
import com.api.miniproject.util.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService service;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("account", new LoginDto());
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(
            @Validated @ModelAttribute(name = "account") LoginDto loginDto, BindingResult bindingResult,
            @RequestParam(defaultValue = "/") String requestURI,
            HttpServletRequest request
    ) {

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "login/loginForm";
        }

        Account findAccount = service.findByAccountId(loginDto.getAccountId(), loginDto.getAccountPw());

        if (findAccount == null) { // 유저 없을 시 global error 반환
            bindingResult.reject("notExistAccount");
            return "login/loginForm";
        }
        setSession(request, findAccount); // 세션 셋팅

        return "redirect:" + requestURI;
    }

    private void setSession(HttpServletRequest request, Account account) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_ACCOUNT, account);
        log.info("save in session={}", session.getAttribute(SessionConst.LOGIN_ACCOUNT));
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if(session != null){
            session.invalidate();
        }

        return "redirect:/login";
    }
}
