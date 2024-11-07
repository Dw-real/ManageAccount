package com.dw.account.infrastructure;

import com.dw.account.domain.*;
import com.dw.account.domain.exception.DuplicateIdException;
import com.dw.account.domain.exception.IdNotFoundException;
import com.dw.account.domain.exception.InfoNotFoundException;
import com.dw.account.domain.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
@Profile("test")
public class ListUserRepository implements UserRepository {

    private final List<User> users = new CopyOnWriteArrayList<>();

    public User register(User user) {
        boolean idExists = users.stream()
                .anyMatch(existingUser -> existingUser.getId().equals(user.getId()));

        if (idExists) {
            throw new DuplicateIdException("이미 존재하는 ID입니다.");
        }

        users.add(user);
        return user;
    }

    public User findById(String id) {
        return users.stream()
                .filter(user -> user.sameId(id))
                .findFirst()
                .orElseThrow(() -> new IdNotFoundException("해당 id로 가입된 정보가 없습니다."));
    }

    public User findByInfo(String name, String phoneNumber) {
        return users.stream()
                .filter(user -> user.sameInfo(name, phoneNumber))
                .findFirst()
                .orElseThrow(() -> new InfoNotFoundException("해당 정보로 가입된 id가 없습니다."));
    }

    public User update(User user, String pwd) {
        User existUser = findById(user.getId());

        existUser.setPwd(pwd);

        return existUser;
    }

    public void delete(String id) {
        User user = this.findById(id);
        users.remove(user);
    }
}
