package com.api.miniproject.util.session;

import com.api.miniproject.domain.User;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {

    /**
     *
     * @return session(default == fasle)
     */
    public static HttpSession getSession(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return servletRequestAttributes.getRequest().getSession(false);
    }

    /**
     *
     * @param create(default == false)
     * @return session
     */
    public static HttpSession getSession(boolean create) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        if(create){
            return servletRequestAttributes.getRequest().getSession(true);
        }
        return servletRequestAttributes.getRequest().getSession(false);
    }

    public static void sessionInvalidate(){
        HttpSession session = getSession();
        if(session != null){
            session.invalidate();
        }
    }

    public static Long getUserSessionId() {
        User user = (User) getSession().getAttribute(SessionConst.LOGIN_USER);
        return user.getId();
    }


}
