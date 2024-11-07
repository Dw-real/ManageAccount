package com.dw.account.domain.repository;

import com.dw.account.domain.User;

public interface UserRepository {
    User register(User user);
    User checkId(String id);
    User findById(String id);
    User findByInfo(String name, String phoneNumber);
    User update(User user, String pwd);
    void delete(String id);
}
