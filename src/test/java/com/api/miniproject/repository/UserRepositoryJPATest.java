package com.api.miniproject.repository;

import com.api.miniproject.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // inMemory database를 연결하는 것이 아닌 사용중인 실제 DB 매칭하도록 변경
@DisplayName("UserRepositoryJPA 관련 테스트")
public class UserRepositoryJPATest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("user 저장 성공 테스트")
    public void saveUser() {
        // given
        User testUser = User.builder()
                .userId("TEST_USER")
                .userName("TEST_USER")
                .userPw("qwerasdf1!").build();

        //when
        User user = userRepository.saveUser(testUser);

        //then
        assertThat(user.getUserId()).isEqualTo("TEST_USER");
    }

}
