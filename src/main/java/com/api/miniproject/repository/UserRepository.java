package com.api.miniproject.repository;

import com.api.miniproject.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    // C user 저장 <- 가입은 이후에
    // R user 찾기 -> optional 로 만들어주기 null 값일 수 있음
    // U
    // D

    User saveUser(User user);
    List<User> findAll(); // 일단은 찾아주면서
    User findById(Long id); // session 에 key 로 저장
    Optional<User> findByUserId(String userId); // session 에 key 로 저장
    void updateUser(User user); // User 객체 받아서
    void deleteUser(Long id); // 로그인 후에만 삭제하니까



}
