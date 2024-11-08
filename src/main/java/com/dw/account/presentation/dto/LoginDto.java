package com.dw.account.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginDto {
    @NotBlank(message = "ID는 필수 입력 항목입니다.")
    @Size(min = 5, max = 15, message = "ID는 5자에서 15자 사이여야 합니다.")
    private String id;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Size(min = 4, max = 15, message = "비밀번호는 4자에서 15자 사이여야 합니다.")
    private String pwd;

    public LoginDto(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }

    public String getId() {
        return id;
    }

    public String getPwd() {
        return pwd;
    }
}
