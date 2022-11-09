package com.api.miniproject.service;

import com.api.miniproject.domain.Account;
import com.api.miniproject.dto.account.JoinDto;
import com.api.miniproject.repository.AccountRepository;
import com.api.miniproject.service.account.AccountServiceImpl;
import com.api.miniproject.util.converter.account.JoinDtoToAccountConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("User service 관련 테스트")
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl userService;

    @Test
    @DisplayName("joinDto -> User 컨버팅 테스트")
    void convertingTest() {
        JoinDtoToAccountConverter converter = new JoinDtoToAccountConverter();

        JoinDto joinDto = JoinDto.builder()
                .accountId("test_id")
                .accountPw("qwerasdf1!")
                .accountName("test_name").build();

        Account convertingUser = converter.convert(joinDto);

        assertThat(joinDto.getAccountId()).isEqualTo(convertingUser.getAccountId());
        assertThat(joinDto.getAccountPw()).isEqualTo(convertingUser.getAccountPw());
    }

    @Test
    @DisplayName("find Account 테스트")
    void findByUserId() {
        when(accountRepository.findByAccountId("TEST_USER")).thenReturn(
                Optional.ofNullable(Account.builder()
                        .accountId("TEST_USER")
                        .accountName("TEST_USER")
                        .accountPw("qwerasdf1!").build()));

        Account test_user = userService.findByAccountId("TEST_USER");

        assertThat(test_user.getAccountId()).isEqualTo("TEST_USER");
        assertThat(test_user.getAccountPw()).isEqualTo("qwerasdf1!");
    }
}
