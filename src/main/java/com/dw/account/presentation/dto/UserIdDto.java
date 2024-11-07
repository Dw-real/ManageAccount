package com.dw.account.presentation.dto;

import jakarta.validation.constraints.NotNull;

public class UserIdDto {
    @NotNull
    private String id;

    public UserIdDto(String id) {
        this.id = id;
    }

    public @NotNull String getId() {
        return id;
    }
}
