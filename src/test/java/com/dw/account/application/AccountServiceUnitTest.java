package com.dw.account.application;

import com.dw.account.application.service.AccountService;
import com.dw.account.application.service.ValidationService;
import com.dw.account.domain.User;
import com.dw.account.domain.repository.UserRepository;
import com.dw.account.presentation.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceUnitTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private AccountService accountService;

    @Test
    @DisplayName("계정 등록 이후 id로 해당 계정 정보가 조회되어야 한다.")
    void accountResisterTest() {
        UserDto userDto = new UserDto("dongwoo", "dongwoo1235", "123456", "19990702", "남자", "010-1234-5678", "gksehddn8@naver.com");

        User user = UserDto.toEntity(userDto);
        when(userRepository.register(any())).thenReturn(user);

        UserDto savedUserDto = accountService.register(userDto);

        assertEquals(savedUserDto.getName(), userDto.getName());
        assertEquals(savedUserDto.getId(), userDto.getId());
        assertEquals(savedUserDto.getPwd(), userDto.getPwd());
        assertEquals(savedUserDto.getBirthDate(), userDto.getBirthDate());
        assertEquals(savedUserDto.getGender(), userDto.getGender());
        assertEquals(savedUserDto.getPhoneNumber(), userDto.getPhoneNumber());
        assertEquals(savedUserDto.getEmail(), userDto.getEmail());
    }
}
