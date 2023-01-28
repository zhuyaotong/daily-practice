package com.github.zhuyaotong.service.impl;

import com.github.zhuyaotong.entity.UserEntity;
import com.github.zhuyaotong.repository.UserRepository;
import com.github.zhuyaotong.service.SubUserService;
import com.github.zhuyaotong.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubUserService subUserService;

    @Override
    // 异常不是RuntimeException的要这样： @Transactional(rollbackFor = Exception.class)
    @Transactional
    public int createUserRight1(String name) throws IOException {
        createUserPrivate(new UserEntity(name));
        throw new RuntimeException("故意抛的异常！");
//        try {
//        } catch (Exception ex) {
//            log.error("create user failed because {}", ex.getMessage());
//            throw ex;
//        }
//        return userRepository.findByName(name).size();
    }

    @Override
    @Transactional
    public void createUserRight2(UserEntity entity) {
        createMainUser(entity);
        try {
            subUserService.createSubUserWithExceptionWrong(entity);
        } catch (Exception ex) {
            log.error("抓住createSubUserWithExceptionWrong", ex);
        }
    }

    private void createMainUser(UserEntity entity) {
        userRepository.save(entity);
        log.info("createMainUser finish");
    }

    public void createUserPrivate(UserEntity entity) throws IOException {
        userRepository.save(entity);
        if (entity.getName().contains("test")) {
        }
//            throw new RuntimeException("invalid username!");
//            throw new IOException("文件不存在");
    }

    //根据用户名查询用户数
    @Override
    public int getUserCount(String name) {
        return userRepository.findByName(name).size();
    }
}