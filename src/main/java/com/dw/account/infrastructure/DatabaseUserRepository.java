package com.dw.account.infrastructure;

import com.dw.account.domain.*;
import com.dw.account.domain.exception.DuplicateIdException;
import com.dw.account.domain.exception.IdNotFoundException;
import com.dw.account.domain.exception.InfoNotFoundException;
import com.dw.account.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Profile("prod")
public class DatabaseUserRepository implements UserRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public DatabaseUserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public User register(User user) {
        SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(user);

        try {
            namedParameterJdbcTemplate.update(
                    "INSERT INTO USER (name, id, pwd, birthDate, gender, phoneNumber, email) VALUES (:name, :id, :pwd, :birthDate, :gender, :phoneNumber, :email)",
                    namedParameter);
        } catch (DuplicateKeyException exception) {
            throw new DuplicateIdException("이미 존재하는 ID입니다.");
        }

        return user;
    }

    public User findById(String id) {
        SqlParameterSource namedParameter = new MapSqlParameterSource("id", id);
        User user = null;

        try {
            user = namedParameterJdbcTemplate.queryForObject(
                    "SELECT * FROM USER WHERE id=:id",
                    namedParameter,
                    new BeanPropertyRowMapper<>(User.class)
            );
        } catch(EmptyResultDataAccessException exception) {
            throw new IdNotFoundException("해당 Id로 가입된 정보가 없습니다.");
        }

        return user;
    }

    public User findByInfo(String name, String phoneNumber) {
        SqlParameterSource namedParameter = new MapSqlParameterSource("name", name)
                .addValue("phoneNumber", phoneNumber);

        User user = null;

        try {
            user = namedParameterJdbcTemplate.queryForObject(
                    "SELECT id FROM USER WHERE name=:name AND phoneNumber=:phoneNumber",
                    namedParameter,
                    new BeanPropertyRowMapper<>(User.class)
            );
        } catch(EmptyResultDataAccessException exception) {
            throw new InfoNotFoundException("해당 정보로 가입된 id가 없습니다.");
        }

        return user;
    }

    public User update(User user, String pwd) {
        // 비밀번호와 ID를 매개변수로 설정
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("pwd", pwd);
        parameters.put("id", user.getId());

        // 업데이트 실행
        namedParameterJdbcTemplate.update("UPDATE USER SET pwd=:pwd WHERE id=:id", parameters);
        user.setPwd(pwd);
        return user;
    }

    public void delete(String id) {
        SqlParameterSource namedParameter = new MapSqlParameterSource("id", id);

        namedParameterJdbcTemplate.update(
                "DELETE FROM USER WHERE id=:id",
                namedParameter
        );
    }
}
