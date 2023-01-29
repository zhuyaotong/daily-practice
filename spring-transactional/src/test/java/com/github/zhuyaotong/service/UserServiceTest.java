package com.github.zhuyaotong.service;

import com.github.zhuyaotong.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void test_createUserRight1() {
        Exception exception = assertThrows(RuntimeException.class, () -> userService.createUserRight1("test"));
        assertEquals("故意抛的异常！", exception.getMessage());
    }

    @Test
    public void test_createUserRight2() {
        UserEntity user = getUser();
        userService.createUserRight2(user);
        assertTrue(userService.getUserCount(user.getName()) != 0);
    }

    @Test
    public void test_createUserRight3() {
        String name = "jay";
        assertTrue(userService.createUserRight3(name) >= 1);
    }

    private UserEntity getUser() {
        return new UserEntity("ae86");
    }
}