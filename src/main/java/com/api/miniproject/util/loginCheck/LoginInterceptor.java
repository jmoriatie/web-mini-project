package com.api.miniproject.util.loginCheck;

import com.api.miniproject.domain.User;
import com.api.miniproject.util.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();

        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute(SessionConst.LOGIN_USER) == null){
            log.info("=== SESSION 없음 ===");
            response.sendRedirect("/login?requestURI=" + requestURI);
            return false;
        }

        log.info("=== SESSION [{}] ===", session.getId());
        return true;
    }
}
