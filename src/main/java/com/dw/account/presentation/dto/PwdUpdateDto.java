package com.dw.account.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PwdUpdateDto {
    @Size(min = 4, max = 15, message = "비밀번호는 4자에서 15자 사이여야 합니다.")
    private String currentPwd;

    @Size(min = 4, max = 15, message = "비밀번호는 4자에서 15자 사이여야 합니다.")
    private String newPwd;

    public PwdUpdateDto(String currentPwd, String newPwd) {
        this.currentPwd = currentPwd;
        this.newPwd = newPwd;
    }

    public String getCurrentPwd() {
        return currentPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }
}
