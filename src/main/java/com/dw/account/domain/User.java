package com.dw.account.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class User {
    @Size(min = 2, max = 50)
    private String name;

    @Size(min = 5, max = 15, message = "아이디는 최소 5자 이상, 최대 15자 이하로 설정해주세요")
    private String id;

    @Size(min = 4, max = 15, message = "비밀번호는 최소 4자 이상, 최대 15자 이하로 설정해주세요")
    private String pwd;

    @Pattern(regexp = "\\d{8}", message = "생년월일은 YYYYMMDD 형식이어야 합니다.")
    @Size(min = 8, max = 8, message = "생년월일은 정확히 8자리여야 합니다.")
    private String birthDate;

    @Pattern(regexp = "남자|여자", message = "성별은 '남자' 또는 '여자'만 허용됩니다.")
    private String gender;

    @Pattern(regexp = "\\d{2,3}-\\d{3,4}-\\d{4}", message = "전화번호는 'XXX-XXXX-XXXX' 형식이어야 합니다.")
    private String phoneNumber;

    @Email(message = "유효한 이메일 주소를 입력해주세요.")
    private String email;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
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

    public Boolean sameId(String id) {
        return this.id.equals(id);
    }

    public Boolean samePwd(String pwd) {
        return this.pwd.equals(pwd);
    }

    public Boolean sameInfo(String name, String phoneNumber) {
        return (this.name.equals(name) && this.phoneNumber.equals(phoneNumber));
    }
}
