package com.dw.account.application.service;

import com.dw.account.domain.User;
import com.dw.account.domain.repository.UserRepository;
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

    public UserDto updatePwd(String id, String pwd) {
        User user = userRepository.findById(id);
        User updatedUser = userRepository.update(user, pwd);
        UserDto userDto = UserDto.toDto(updatedUser);
        return userDto;
    }

    public void delete(String id) {
        userRepository.delete(id);
    }
}
