package com.api.miniproject.controller;

import com.api.miniproject.domain.User;
import com.api.miniproject.dto.login.LoginDto;
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
        model.addAttribute("loginDto", new LoginDto());
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(
            @Validated @ModelAttribute LoginDto loginDto, BindingResult bindingResult,
            @RequestParam(defaultValue = "/") String requestURI,
            HttpServletRequest request
    ) {

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "/login/loginForm";
        }

        User findUser = service.findByUserId(loginDto.getUserId(), loginDto.getUserPw());

        // 유저 없을 시 global error 반환
        if (findUser == null) {
            bindingResult.reject("notExistUser");
            return "/login/loginForm";
        }

        // TODO session 에는 필요한 정보만 저장하도록 세팅
        setSession(request, findUser); // 세션 셋팅

        return "redirect:" + requestURI;
    }

    private void setSession(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER, user);
        log.info("save in session={}", session.getAttribute(SessionConst.LOGIN_USER));
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
