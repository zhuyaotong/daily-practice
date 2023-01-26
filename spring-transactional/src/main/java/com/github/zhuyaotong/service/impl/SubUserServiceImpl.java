package com.github.zhuyaotong.service.impl;

import com.github.zhuyaotong.entity.UserEntity;
import com.github.zhuyaotong.repository.UserRepository;
import com.github.zhuyaotong.service.SubUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class SubUserServiceImpl implements SubUserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void createSubUserWithExceptionWrong(UserEntity entity) {
        log.info("createSubUserWithExceptionWrong start");
        userRepository.save(entity);
        throw new RuntimeException("invalid status");
    }

}
