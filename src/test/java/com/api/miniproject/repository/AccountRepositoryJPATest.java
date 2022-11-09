package com.api.miniproject.repository;

import com.api.miniproject.domain.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // inMemory database를 연결하는 것이 아닌 사용중인 실제 DB 매칭하도록 변경
@DisplayName("UserRepositoryJPA 관련 테스트")
public class AccountRepositoryJPATest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DisplayName("user 저장 성공 테스트")
    public void saveUser() {
        // given
        Account testAccount = Account.builder()
                .accountId("TEST_USER")
                .accountName("TEST_USER")
                .accountPw("qwerasdf1!").build();

        //when
        Account user = accountRepository.saveAccount(testAccount);

        //then
        assertThat(user.getAccountId()).isEqualTo("TEST_USER");
    }

}
