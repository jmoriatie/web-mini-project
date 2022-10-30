package com.api.miniproject.repository;

import com.api.miniproject.domain.User;
import com.api.miniproject.dto.user.JoinDto;
import com.api.miniproject.repository.jpa.UserRepositoryJPAImpl;
import com.api.miniproject.service.user.UserService;
import com.api.miniproject.service.user.UserServiceImpl;
import com.api.miniproject.util.converter.user.JoinDtoToUserConverter;
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
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("joinDto -> User 컨버팅 테스트")
    void convertingTest() {
        JoinDtoToUserConverter converter = new JoinDtoToUserConverter();

        JoinDto joinDto = JoinDto.builder()
                .userId("test_id")
                .userPw("qwerasdf1!")
                .userName("test_name").build();

        User convertingUser = converter.convert(joinDto);

        assertThat(joinDto.getUserId()).isEqualTo(convertingUser.getUserId());
        assertThat(joinDto.getUserPw()).isEqualTo(convertingUser.getUserPw());
    }

    @Test
    @DisplayName("find user 테스트")
    void findByUserId() {
        when(userRepository.findByUserId("TEST_USER")).thenReturn(
                Optional.ofNullable(User.builder()
                        .userId("TEST_USER")
                        .userName("TEST_USER")
                        .userPw("qwerasdf1!").build()));

        User test_user = userService.findByUserId("TEST_USER");

        assertThat(test_user.getUserId()).isEqualTo("TEST_USER");
        assertThat(test_user.getUserPw()).isEqualTo("qwerasdf1!");
    }







}
