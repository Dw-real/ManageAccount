package com.dw.account.application.service;

import com.dw.account.domain.User;
import com.dw.account.domain.exception.IdNotFoundException;
import com.dw.account.domain.exception.InvalidPassWordException;
import com.dw.account.domain.exception.LoginException;
import com.dw.account.domain.repository.UserRepository;
import com.dw.account.presentation.dto.LoginDto;
import com.dw.account.presentation.dto.UserDto;
import com.dw.account.presentation.dto.UserIdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private UserRepository userRepository;
    private ValidationService validationService;

    @Autowired
    public AccountService(UserRepository userRepository, ValidationService validationService) {
        this.userRepository = userRepository;
        this.validationService = validationService;
    }

    public void logIn(LoginDto loginDto) {
        User user = userRepository.findById(loginDto.getId());

        // ID가 존재하지 않으면 IdNotFoundException을 던지기
        if (user == null) {
            throw new IdNotFoundException("현재 ID로 등록된 아이디가 없습니다.");
        }

        if (!user.samePwd(loginDto.getPwd())) {
            throw new LoginException("비밀번호가 일치하지 않습니다.");
        }
    }

    public UserDto register(UserDto userDto) {
        // Dto -> Entity
        User user = UserDto.toEntity(userDto);
        validationService.checkValid(user);
        // repository 호출
        User savedUser = userRepository.register(user);
        // Entity -> Dto
        UserDto savedUserDto = UserDto.toDto(savedUser);
        // Dto 리턴
        return savedUserDto;
    }

    public String checkId(String id) {
        User user = userRepository.checkId(id);

        if (user == null) {
            return "ok";
        } else {
            return "no";
        }
    }

    public UserDto findById(String id) {
        User user = userRepository.findById(id);

        if (user == null) {
            return null;
        }

        // Dto 변환
        UserDto userDto = UserDto.toDto(user);
        // Dto 리턴
        return userDto;
    }

    public UserIdDto findByInfo(String name, String phoneNumber) {
        User user = userRepository.findByInfo(name, phoneNumber);
        UserIdDto userIdDto = new UserIdDto(user.getId());
        return userIdDto;
    }

    public UserDto updatePwd(String id, String pwd, String newPwd) {
        User user = userRepository.findById(id);
        if (!user.samePwd(pwd)) {
            throw new InvalidPassWordException("현재 비밀번호가 일치하지 않습니다.");
        }
        User updatedUser = userRepository.update(user, newPwd);
        UserDto userDto = UserDto.toDto(updatedUser);
        return userDto;
    }

    public void delete(String id) {
        userRepository.delete(id);
    }
}
