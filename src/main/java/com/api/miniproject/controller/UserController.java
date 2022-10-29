package com.api.miniproject.controller;

import com.api.miniproject.dto.login.JoinDto;
import com.api.miniproject.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/join")
    public String joinFrom(Model model){
        model.addAttribute("user", new JoinDto());
        return "login/joinForm";
    }

    @PostMapping("/join")
    public String join(@Validated @ModelAttribute("user") JoinDto joinDto, BindingResult bindingResult,
                       RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "login/joinForm";
        }

        // 있는 id check

        return "redirect:/";
    }
}
