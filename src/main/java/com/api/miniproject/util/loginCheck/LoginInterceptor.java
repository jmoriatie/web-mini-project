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

        log.info("=== preHandler ===");
        HttpSession session = request.getSession(false);

        if(session == null){
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}
