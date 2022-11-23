package com.api.miniproject.repository;

import com.api.miniproject.domain.Account;
import com.api.miniproject.repository.jpa.AccountRepositoryJPA;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// TODO: JPA 상속 없애기
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>, AccountRepositoryJPA {

    @EntityGraph(attributePaths = "authorities")
    Optional<Account> findOneWithAuthoritiesByAccountId(String accountId);
}
