package com.korit.korit_gov_servlet_study.ch09.user.dto;

import com.korit.korit_gov_servlet_study.ch09.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SignupReqDto {
    private String username;
    private String password;
    private int age;
    // 사용자한테 받을 것

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .age(age)
                .build(); //이렇게 만들고 나면
    }
}
