package com.api.miniproject.controller;

import com.api.miniproject.domain.User;
import com.api.miniproject.util.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {

    // item 관련 메서드에 접근하기 전에 login 확인하는 aop 작성 -> interceptor로 바꿈..
    @GetMapping
    public String home(HttpServletRequest request, RedirectAttributes redirectAttributes){
        HttpSession session = request.getSession(false);

        User loginUser = (User)session.getAttribute(SessionConst.LOGIN_USER);
        log.info("loginUser id:{}, pw:{}, name:{}", loginUser.getUserId(), loginUser.getUserPw(), loginUser.getUserName());

        redirectAttributes.addAttribute("loginUser", true);
        redirectAttributes.addAttribute("loginUserName", loginUser.getUserName());

        return "index";
    }
}
