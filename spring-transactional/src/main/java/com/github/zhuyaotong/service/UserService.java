package com.github.zhuyaotong.service;

import com.github.zhuyaotong.entity.UserEntity;

import java.io.IOException;

public interface UserService {

    int createUserRight1(String name) throws IOException;

    void createUserRight2(UserEntity entity);

    int createUserRight3(String name);

    int getUserCount(String name);


}
