package com.api.miniproject.repository;

import com.api.miniproject.domain.Item;
import com.api.miniproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryJPA {

    @Query(value = "SELECT * FROM USER_TB", nativeQuery = true)
    List<User> findAll();
}
