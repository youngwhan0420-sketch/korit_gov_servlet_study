package com.korit.korit_gov_servlet_study.ch09.user.service;

import com.korit.korit_gov_servlet_study.ch09.user.dao.UserDao;
import com.korit.korit_gov_servlet_study.ch09.user.dto.SignupReqDto;
import com.korit.korit_gov_servlet_study.ch09.user.entity.User;

import java.util.Optional;

public class UserService {
    private static UserService instance;
    private UserDao userDao;

    public UserService () {
        userDao = UserDao.getInstance();
    }
    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public boolean isDuplicatedUsername(String username){
        // 중복인지 아닌지 판단하려면 가져온  username의 값을 가지고 있는 유저가 있는 지 확인 해봐야 한다.
        Optional<User> foundUser = userDao.findByUsername(username);
        return foundUser.isPresent();
    }

    //추가하는 메소드
    public User addUser(SignupReqDto signupDto) {
        //여기서는 딱히 비즈니스 로직이 필요가 없다. 그냥 추가하면 된다.
        return userDao.addUser(signupReq)
    }

}
