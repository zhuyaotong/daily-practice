package com.github.zhuyaotong.springboot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainTest {

    @Test
    public void test_MyConfiguration() {
        Assert.assertNotNull(MyConfiguration.class);
    }
}
