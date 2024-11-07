package com.dw.account.presentation.dto;

import com.dw.account.domain.User;
import jakarta.validation.constraints.NotNull;

public class UserDto {
    @NotNull
    private String name;
    @NotNull
    private String id;
    @NotNull
    private String pwd;
    @NotNull
    private String birthDate;
    @NotNull
    private String gender;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String email;

    public UserDto(String name, String id, String pwd, String birthDate, String gender, String phoneNumber, String email) {
        this.name = name;
        this.id = id;
        this.pwd = pwd;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPwd() {
        return pwd;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setId(userDto.getId());
        user.setPwd(userDto.getPwd());
        user.setBirthDate(userDto.getBirthDate());
        user.setGender(userDto.getGender());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setEmail(userDto.getEmail());

        return user;
    }

    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto(user.getName(),
                user.getId(),
                user.getPwd(),
                user.getBirthDate(),
                user.getGender(),
                user.getPhoneNumber(),
                user.getEmail());

        return userDto;
    }
}
