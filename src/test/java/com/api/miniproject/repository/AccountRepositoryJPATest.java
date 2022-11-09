package com.api.miniproject.repository;

import com.api.miniproject.domain.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@DisplayName("UserRepositoryJPA 관련 테스트")
@TestPropertySource("classpath:application-test.properties")
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
