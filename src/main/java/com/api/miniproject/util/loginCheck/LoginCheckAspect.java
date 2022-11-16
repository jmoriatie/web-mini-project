package com.api.miniproject.util.loginCheck;

import com.api.miniproject.domain.Account;
import com.api.miniproject.util.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Slf4j
@Aspect
//@Component // intercepor 로 대체
public class LoginCheckAspect {

    @Pointcut("@annotation(com.api.miniproject.util.loginCheck.LoginCheck)")
    public void LoginCheck(){
    }

    @Before("LoginCheck()")
    public void loginCheck() throws IOException {
        // ServletWebRequest: 결론적으론 request response 를 다 가지고 있는 애라고 보면 됨 : ServletRequestAttributes 를 상속받는데 얘가 다가지고 있음
        // RequestContextHolder.currentRequestAttributes: 최근 요청을 key(thread) value(HttpServletRequest) 형태로 보관하다가 쓰고 제거
        log.debug("===== AOP User Check =====");

        ServletRequestAttributes att = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();

//        ServletWebRequest servletWebRequest = new ServletWebRequest();
        HttpSession session = att.getRequest().getSession();
        Account account = (Account)session.getAttribute(SessionConst.LOGIN_ACCOUNT);

        if(account == null){
            att.getResponse().sendRedirect(att.getRequest().getContextPath() + "/login");
//            throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED, "로그인 실패"){};
        }
    }
}
