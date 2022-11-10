package com.api.miniproject.controller.account;

import com.api.miniproject.domain.Account;
import com.api.miniproject.dto.account.JoinDto;
import com.api.miniproject.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;
    private final ConversionService conversionService;

    @GetMapping("/join")
    public String joinFrom(Model model) {
        model.addAttribute("user", new JoinDto());
        return "login/joinForm";
    }

    @PostMapping("/join")
    public String join(@Validated @ModelAttribute("user") JoinDto joinDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "login/joinForm";
        }

        Account findUser = service.findByAccountId(joinDto.getAccountId());
        if (findUser != null) { // 있는 id check
            bindingResult.reject("existUser");
            return "login/joinForm";
        }

        Account joinAccount = conversionService.convert(joinDto, Account.class);
        service.saveAccount(joinAccount);
        log.info("Account 저장={}", joinAccount);

        return "redirect:/";
    }
}
