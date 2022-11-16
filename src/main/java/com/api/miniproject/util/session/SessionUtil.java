package com.api.miniproject.util.session;

import com.api.miniproject.domain.Account;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * 사용 X
 */
public class SessionUtil {

    /**
     * @return session(default == fasle)
     */
    public static HttpSession getSession(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return servletRequestAttributes.getRequest().getSession(false);
    }

    /**
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

    /**
     * 사용X
     */
    public static void sessionInvalidate(){
        HttpSession session = getSession();
        if(session != null){
            session.invalidate();
        }
    }

    public static Long getUserIdFromSession() {
        Account account = (Account) getSession().getAttribute(SessionConst.LOGIN_ACCOUNT);
        return account.getId();
    }


}
