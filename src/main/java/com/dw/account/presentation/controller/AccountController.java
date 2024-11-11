package com.dw.account.presentation.controller;

import com.dw.account.application.service.AccountService;
import com.dw.account.domain.User;
import com.dw.account.domain.exception.IdNotFoundException;
import com.dw.account.domain.exception.InvalidPassWordException;
import com.dw.account.domain.exception.LoginException;
import com.dw.account.presentation.dto.LoginDto;
import com.dw.account.presentation.dto.PwdUpdateDto;
import com.dw.account.presentation.dto.UserDto;
import com.dw.account.presentation.dto.UserIdDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/accounts/login", method = RequestMethod.POST)
    public ResponseEntity<?> logIn(@Valid @RequestBody LoginDto loginDto) {
        accountService.logIn(loginDto);
        UserDto userDto = accountService.findById(loginDto.getId());
        return ResponseEntity.ok(userDto);
    }

    @RequestMapping(value = "/accounts/id-check", method = RequestMethod.GET)
    public @ResponseBody String idCheck(@RequestParam("id") String id) {
        return accountService.checkId(id);
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
    public UserDto updatePassword(@PathVariable String id, @RequestBody PwdUpdateDto pwdUpdateDto) {
        return accountService.updatePwd(id, pwdUpdateDto.getCurrentPwd(), pwdUpdateDto.getNewPwd());
    }

    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.DELETE)
    public void deleteAccount(@PathVariable String id) {
        accountService.delete(id);
    }
}
