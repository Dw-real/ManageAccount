package com.dw.account.application;

import com.dw.account.application.service.AccountService;
import com.dw.account.domain.exception.DuplicateIdException;
import com.dw.account.domain.exception.IdNotFoundException;
import com.dw.account.domain.exception.InfoNotFoundException;
import com.dw.account.presentation.dto.UserDto;
import com.dw.account.presentation.dto.UserIdDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("prod")
class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Test
    @DisplayName("이미 등록되어 있는 id로 회원가입을 시도하면 DuplicateIdException이 발생해야한다.")
    void registerDuplicateIdTest() {
        String existId = "gksehddn8";

        assertThrows(DuplicateIdException.class, () -> {
            accountService.register(new UserDto("dongwoo", existId, "1234", "19990703", "남자", "010-2222-3333", "gksehddn8@gmail.com"));
        });
    }

    //@Transactional
    @Test
    @DisplayName("계정 등록 이후 id로 조회하면 해당 계정 정보가 조회되어야 한다.")
    void accountResisterAndFindByIdTest() {
        UserDto userDto = new UserDto("dongwoo", "dongwoo1235", "123456", "19990702", "남자", "010-1234-5678", "gksehddn8@naver.com");

        UserDto savedUserDto = accountService.register(userDto);
        String savedUserId = savedUserDto.getId();

        UserDto foundUserDto = accountService.findById(savedUserId);

        assertEquals(savedUserDto.getName(), foundUserDto.getName());
        assertEquals(savedUserDto.getId(), foundUserDto.getId());
        assertEquals(savedUserDto.getPwd(), foundUserDto.getPwd());
        assertEquals(savedUserDto.getBirthDate(), foundUserDto.getBirthDate());
        assertEquals(savedUserDto.getGender(), foundUserDto.getGender());
        assertEquals(savedUserDto.getPhoneNumber(), foundUserDto.getPhoneNumber());
        assertEquals(savedUserDto.getEmail(), foundUserDto.getEmail());
    }

    @Test
    @DisplayName("존재하지 않는 user id로 조회하면 IdNotFoundException이 발생해야한다.")
    void findUserNotExistIdTest() {
        String notExistId = "test1";

        assertThrows(IdNotFoundException.class, () -> {
            accountService.findById(notExistId);
        });
    }

    @Transactional
    @Test
    @DisplayName("계정 등록 이후 이름과 전화번호로 조회하면 해당 계정의 id가 조회되어야 한다.")
    void accountResisterAndFindByInfoTest() {
        UserDto userDto = new UserDto("dongwoo", "dongwoo1235", "123456", "19990702", "남자", "010-1234-5678", "gksehddn8@naver.com");

        UserDto savedUserDto = accountService.register(userDto);
        String savedUserName = savedUserDto.getName();
        String savedPhoneNumber = savedUserDto.getPhoneNumber();

        UserIdDto foundUserIdDto = accountService.findByInfo(savedUserName, savedPhoneNumber);

        assertEquals(savedUserDto.getId(), foundUserIdDto.getId());
    }

    @Test
    @DisplayName("등록되지 않은 이름,전화번호로 조회하면 InfoNotFoundException이 발생해야한다.")
    void findUserNotExistInfoTest() {
        String notExistName = "han";
        String notExistPhoneNumber = "010-1234-5678";

        assertThrows(InfoNotFoundException.class, () -> {
            accountService.findByInfo(notExistName, notExistPhoneNumber);
        });
    }
}