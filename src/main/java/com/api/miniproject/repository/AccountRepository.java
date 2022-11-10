package com.api.miniproject.repository;

import com.api.miniproject.domain.Account;
import com.api.miniproject.repository.jpa.AccountRepositoryJPA;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// TODO: JPA 상속 없애기
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>, AccountRepositoryJPA {

    @Query(value = "SELECT * FROM USER_TB", nativeQuery = true)
    List<Account> findAll();

    @EntityGraph(attributePaths = "authorities")
    Optional<Account> findOneWithAuthoritiesByAccountName(String accountName);
}
