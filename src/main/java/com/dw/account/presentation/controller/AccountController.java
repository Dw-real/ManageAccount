package com.dw.account.presentation.controller;

import com.dw.account.application.service.AccountService;
import com.dw.account.presentation.dto.UserDto;
import com.dw.account.presentation.dto.UserIdDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public UserDto createAccount(@Valid @RequestBody UserDto userDto) {
        return accountService.register(userDto);
    }

    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
    public UserDto findUserById(@PathVariable String id) {
        return accountService.findById(id);
    }

    @RequestMapping(value = "/accounts/{name}/{phoneNumber}", method = RequestMethod.GET)
    public UserIdDto findUserByInfo(@PathVariable String name, @PathVariable String phoneNumber) {
        return accountService.findByInfo(name, phoneNumber);
    }

    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.PATCH)
    public UserDto updatePassword(@PathVariable String id, @RequestBody UserDto userDto) {
        return accountService.updatePwd(id, userDto.getPwd());
    }

    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.DELETE)
    public void deleteAccount(@PathVariable String id) {
        accountService.delete(id);
    }
}
