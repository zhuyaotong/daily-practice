package com.github.zhuyaotong.service;

import com.github.zhuyaotong.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void test_createUserWrong1_nameParameterIsInvalid() {
        assertEquals(0, userService.getUserCount("test"));

        Exception exception = assertThrows(RuntimeException.class, () -> userService.createUserRight1("test"));
        assertEquals("故意抛的异常！", exception.getMessage());

        assertEquals(0, userService.getUserCount("test"));
    }

    @Test
    public void test_createUserWrong1_nameParameterIsValid() throws IOException {
        String name = "jay";
        assertTrue(userService.createUserRight1(name) >= 1);
    }

    @Test
    public void test_createUserWrong2() {
        UserEntity user = getUser();
        userService.createUserRight2(user);
        assertTrue(userService.getUserCount(user.getName()) != 0);
    }

    private UserEntity getUser() {
        return new UserEntity("ae86");
    }
}