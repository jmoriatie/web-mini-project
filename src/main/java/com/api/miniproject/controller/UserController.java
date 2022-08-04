package com.api.miniproject.controller;

import com.api.miniproject.dto.LoginDto;
import com.api.miniproject.service.UserService;
import com.api.miniproject.util.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService service;

    @GetMapping
    public String loginForm(){
        return "login/loginForm";
    }

    @PostMapping
    public String login(
            @ModelAttribute @Validated LoginDto dto,
            BindingResult bindingResult,
            HttpServletRequest request,
            @RequestParam(defaultValue = "/") String requestURL
            ){
        HttpSession session = request.getSession();

        if(dto == null){
            bindingResult.reject("변수", "진짜 메세지");
        }

        session.setAttribute(SessionConst.LOGIN_USER, dto);


        return "/";
    }

    @PostConstruct
    void setTestId(){

    }
}
