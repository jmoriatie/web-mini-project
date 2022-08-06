package com.api.miniproject.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User {
    Long id;
    String userId;
    String userPw;
    String userName;

    public User(String userId, String userPw, String userName) {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
    }

    public void update(User user){
        this.userPw = userPw;
        this.userName = userName;
    }

}
