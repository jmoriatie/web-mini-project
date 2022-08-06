package com.api.miniproject.controller;

import com.api.miniproject.domain.User;
import com.api.miniproject.dto.LoginDto;
import com.api.miniproject.service.UserService;
import com.api.miniproject.util.LoginValidationUtil;
import com.api.miniproject.util.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;


    @GetMapping
    public String loginForm(Model model){
        model.addAttribute("loginDto", new LoginDto());
        return "login/loginForm";
    }

    @PostMapping
    public String login(
            @ModelAttribute LoginDto loginDto,
            BindingResult bindingResult,
            HttpServletRequest request,
            @RequestParam(defaultValue = "/item/items") String requestURL
            ){

        log.debug("dto info = id:{} / pw:{}", loginDto.getUserId(), loginDto.getUserPw());

        User user = service.login(loginDto.getUserId(), loginDto.getUserPw());

        LoginValidationUtil loginValidationUtil = new LoginValidationUtil();

        if(loginValidationUtil.validation(loginDto, bindingResult)){
            log.debug("hasError={}", "에러에러");
            return "/login/loginForm";
        }
        log.info("login user = id:{} / pw:{}", user.getId(), user.getUserPw());

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER, user);
        log.info("save in session={}", session.getAttribute(SessionConst.LOGIN_USER));

        return "redirect:" + requestURL;
    }

    @PostConstruct
    void setTestId(){
        User user1 = new User("test", "test", "NaTest");
        User user2 = new User("a", "a", "김에이");

        service.saveUser(user1);
        service.saveUser(user2);

        log.debug("테스트 유저1 생성={}", user1.toString());
        log.debug("테스트 유저2 생성={}", user2.toString());
    }
}
