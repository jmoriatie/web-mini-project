package com.api.miniproject.service.user;

import com.api.miniproject.domain.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    public User findByUserId(String userId);
}
