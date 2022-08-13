package com.api.miniproject.controller;

import com.api.miniproject.domain.User;
import com.api.miniproject.dto.LoginDto;
import com.api.miniproject.service.UserService;
import com.api.miniproject.util.validation.LoginValidationUtil;
import com.api.miniproject.util.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/login")
    public String loginForm(Model model, HttpServletRequest request) {
        model.addAttribute("loginDto", new LoginDto());
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(
            @ModelAttribute LoginDto loginDto,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        // TODO: 리펙토링 필요, 코드 지저분
        if (!StringUtils.hasText(loginDto.getUserId())){
            bindingResult.addError(new FieldError("loginDto", "userId", "아이디를 입력해주세요"));
        }

        if (!StringUtils.hasText(loginDto.getUserPw())){
            bindingResult.addError(new FieldError("loginDto", "userPw", "비밀번호를 입력해주세요"));
        }

        User user = service.login(loginDto.getUserId(), loginDto.getUserPw());
        log.debug("user?! : {}", user);

        if(user == null){
            bindingResult.addError(new ObjectError("loginDto", "아이디 또는 비밀번호를 확인하세요."));
        }

        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            return "/login/loginForm";
        }

        this.setSession(request, user);


        return "redirect:/";
    }

    private void setSession(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER, user);
        log.info("save in session={}", session.getAttribute(SessionConst.LOGIN_USER));
        log.info("login user = id:{} / pw:{}", user.getId(), user.getUserPw());
    }

    @GetMapping("/logout")
    public String logout() {
        service.logout();
        return "redirect:/login";
    }

    @PostConstruct
    void setTestId() {
        User user1 = new User("test", "test", "NaTest");
        User user2 = new User("a", "a", "김에이");

        service.saveUser(user1);
        service.saveUser(user2);

        log.debug("테스트 유저1 생성={}", user1.toString());
        log.debug("테스트 유저2 생성={}", user2.toString());
    }
}
